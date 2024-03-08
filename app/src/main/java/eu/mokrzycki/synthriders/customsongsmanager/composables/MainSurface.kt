package eu.mokrzycki.synthriders.customsongsmanager.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import eu.mokrzycki.synthriders.customsongsmanager.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainSurface(
    mainViewModel: MainViewModel,
) {
    val fileListState = mainViewModel.fileListState.collectAsState()
    val progress = mainViewModel.progress.collectAsState()
    val songs = mainViewModel.songs.collectAsState()

    Scaffold(
        topBar = {
            MainTopBar {
                ToolbarFirstRow()
                ToolbarSecondRow(
                    songs = songs.value,
                    chips = mainViewModel.chips
                ) { difficulty, isSelected ->
                    mainViewModel.updateChips(difficulty, isSelected)
                    mainViewModel.updateSongs(0, 0)
                    mainViewModel.updateProgress(0f)
                    mainViewModel.debouncedSwitchStateTo(FileListState.Loading(SYNCING))
                }
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val fileListStateValue = fileListState.value) {
                is FileListState.Idle -> {
                    Log.d("synthrider", "is FileListState.Idle")
                    mainViewModel.switchStateTo(FileListState.Loading(FETCHING))
                }

                is FileListState.Loading -> {
                    Log.d("synthrider", "is FileListState.Loading")
                    LoadingListView(
                        progress = progress.value,
                        text = fileListStateValue.text
                    )
                    RequestPermissions { permissionGranted ->
                        if (permissionGranted) {
                            mainViewModel.viewModelScope.launch {
                                mainViewModel.fetchFiles()
                            }
                        } else {
                            // TODO: ...
                        }
                    }
                }

                is FileListState.Data -> {
                    Log.d("synthrider", "is FileListState.Data")
                    mainViewModel.updateSongs(
                        fileListStateValue.musicDBList.size,
                        fileListStateValue.totalSize
                    )
                    CreateViewOnData(fileListStateValue)
                }

                is FileListState.Empty -> {
                    Log.d("synthrider", "is FileListState.Empty")
                    mainViewModel.updateSongs(0, 0)
                    EmptyListView(fileListStateValue.text)
                }
            }
        }
    }
}