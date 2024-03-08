package eu.mokrzycki.synthriders.customsongsmanager.composables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CreateViewOnData(
    fileListState: FileListState.Data,
) {
    LazyColumn(
        Modifier
            .fillMaxHeight()
    ) {
        items(fileListState.musicDBList) {
            MusicRowItem(
                musicCoverUrl = it.coverUrl,
                musicDifficulties = it.difficulties,
                musicTitle = it.title,
                musicArtist = it.artist,
                musicTime = it.duration,
            )
        }
    }
}
