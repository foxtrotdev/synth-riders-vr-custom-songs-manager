package eu.mokrzycki.synthriders.customsongsmanager

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.mokrzycki.synthriders.customsongsmanager.api.ApiService
import eu.mokrzycki.synthriders.customsongsmanager.composables.FileListState
import eu.mokrzycki.synthriders.customsongsmanager.composables.NO_FILES
import eu.mokrzycki.synthriders.customsongsmanager.composables.NO_MATCHING_SONGS
import eu.mokrzycki.synthriders.customsongsmanager.db.MusicDB
import eu.mokrzycki.synthriders.customsongsmanager.extensions.disableExtension
import eu.mokrzycki.synthriders.customsongsmanager.extensions.enableExtension
import eu.mokrzycki.synthriders.customsongsmanager.extensions.getFileIdRegexped
import eu.mokrzycki.synthriders.customsongsmanager.extensions.listFilesNotDirectories
import eu.mokrzycki.synthriders.customsongsmanager.extensions.toMusicDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@OptIn(FlowPreview::class)
class MainViewModel(
    private val repository: MusicRepository,
    private val apiService: ApiService,
) : ViewModel() {

    private val _songs = MutableStateFlow(Pair(1, 1))
    val songs: StateFlow<Pair<Int, Int>> = _songs

    private val _fileListState = MutableStateFlow<FileListState>(FileListState.Idle)
    val fileListState: StateFlow<FileListState> = _fileListState

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress

    private val _chips = mutableMapOf(
        "Easy" to false,
        "Normal" to false,
        "Hard" to false,
        "Expert" to false,
        "Master" to false,
        "Custom" to false,
    )
    val chips: Map<String, Boolean> = _chips

    private val _debouncedFileListState = MutableSharedFlow<FileListState>()

    init {
        viewModelScope.launch {
            _debouncedFileListState
                .debounce(1000) // debounce for 1 second
                .collectLatest { state ->
                    switchStateTo(state)
                }
        }
    }

    private val customSongsFolder by lazy {
        File(Environment.getExternalStorageDirectory(), "SynthRidersUC/CustomSongs/")
    }

    fun insert(musicDB: MusicDB) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(musicDB)
    }

    private suspend fun fetchMetadataWithUpdating(files: List<File>) = withContext(Dispatchers.IO) {
        val musicDBMutableList = mutableListOf<MusicDB>()

        files.forEachIndexed { index, file ->
            val regexpFileId = file.name.getFileIdRegexped()
            Log.d("synthrider", "regexpFileId = $regexpFileId [${index + 1}/${files.size}]")

            _progress.value = ((index + 1f) / (files.size))

            regexpFileId?.let { musicId ->
                val existsInDatabase = repository.existsInDatabase(musicId)
                Log.d("synthrider", "item in database: $existsInDatabase")

                if (!existsInDatabase) {
                    val response = apiService.getBeatmapById(musicId).execute()
                    val successful = response.isSuccessful
                    Log.d("synthrider", "feteched metadata successful: $successful")

                    if (successful) {
                        response.body()?.let {
                            musicDBMutableList.add(element = it.toMusicDB())
                        }
                    } else {
                        Log.d("synthrider", "getBeatmapById response unsuccessful")
                    }
                } else {
                    Log.d("synthrider", "id $regexpFileId already in database")
                }
            }
        }

        return@withContext musicDBMutableList
    }

    private suspend fun getAllByIds(ids: List<Int>): List<MusicDB> = withContext(Dispatchers.IO) {
        Log.d("synthrider", "query to list ${ids.size} items from database")
        return@withContext repository.getAllByIds(ids)
    }

    private suspend fun insertMusicMetadata(musicDBMutableList: MutableList<MusicDB>) {
        Log.d("synthrider", "items size to insert: ${musicDBMutableList.size}")
        if (musicDBMutableList.size > 0) {
            repository.insertAll(musicDBMutableList)
        }
    }

    suspend fun fetchFiles() = withContext(Dispatchers.IO) {
        val fileList = customSongsFolder.listFilesNotDirectories()
        Log.d("synthrider", "fileList: ${fileList.size}")

        if (fileList.isEmpty()) {
            withContext(Dispatchers.Main) {
                switchStateTo(FileListState.Empty(NO_FILES))
            }
        } else {
            fetchAndInsertMetadata(fileList)
            filterAndSynchronizeFiles(fileList)
        }
    }

    private suspend fun filterAndSynchronizeFiles(fileList: List<File>) = withContext(Dispatchers.IO) {
        val fileIdList = fileList.mapNotNull { it.name.getFileIdRegexped() }
        Log.d("synthrider", "fileListIds: ${fileIdList.size}")

        val musicDBList = getAllByIds(fileIdList)
        Log.d("synthrider", "musicDBList: ${musicDBList.size}")

        val filteredDBList = filterDBList(musicDBList, chips)
        Log.d("synthrider", "filteredDBList: ${filteredDBList.size}")

        synchronizeFiles(filteredDBList)

        if (filteredDBList.isEmpty()) {
            withContext(Dispatchers.Main) {
                switchStateTo(FileListState.Empty(NO_MATCHING_SONGS))
            }
        } else {
            withContext(Dispatchers.Main) {
                switchStateTo(FileListState.Data(filteredDBList, musicDBList.size))
            }
        }
    }

    private suspend fun fetchAndInsertMetadata(fileList: List<File>) = withContext(Dispatchers.IO) {
        val musicMetadataList = fetchMetadataWithUpdating(fileList)
        Log.d("synthrider", "musicMetadataList: ${musicMetadataList.size}")
        if (musicMetadataList.size > 0) insertMusicMetadata(musicMetadataList)
    }

    fun switchStateTo(fileState: FileListState) {
        _fileListState.value = fileState
    }

    fun debouncedSwitchStateTo(fileState: FileListState) = viewModelScope.launch {
        _debouncedFileListState.emit(fileState)
    }

    fun updateChips(difficulty: String, isSelected: Boolean) {
        _chips.apply {
            this[difficulty] = isSelected
        }
    }

    /**
     * Filter the musicDBList based on the chips.
     * @param chipBlackList the chips to filter the musicDBList
     * @return the new state of the musicDBList
     */
    private suspend fun filterDBList(
        musicDBS: List<MusicDB>,
        chipBlackList: Map<String, Boolean>,
    ): List<MusicDB> = withContext(Dispatchers.IO) {
        val newMusicDBList = if (chipBlackList.all { !it.value }) {
            musicDBS
        } else {
            val chipsDifficultiesBlacklisted = chipBlackList.entries
                .filter { it.value } // filter only chips with value true
                .map { mapItem -> mapItem.key }

            musicDBS.filterNot { musicDB ->
                musicDB.difficulties.any { it.isNotBlank() && it in chipsDifficultiesBlacklisted }
            }
        }

        return@withContext (newMusicDBList)
    }

    private suspend fun synchronizeFiles(musicDBS: List<MusicDB>) = withContext(Dispatchers.IO) {
        val whitelistedIdList = musicDBS.map { it.id }

        customSongsFolder.listFilesNotDirectories().forEach {
            it.name.getFileIdRegexped()?.let { id ->
                if (id in whitelistedIdList) {
                    it.enableExtension()
                } else {
                    it.disableExtension()
                }
            }
        }

    }

    fun updateProgress(progress: Float) {
        _progress.value = progress
    }

    fun updateSongs(size: Int, sizeTotal: Int) {
        _songs.value = Pair(size, sizeTotal)
    }

}

