package eu.mokrzycki.synthriders.customsongsmanager.composables

import androidx.compose.ui.text.AnnotatedString
import eu.mokrzycki.synthriders.customsongsmanager.db.MusicDB

sealed class FileListState {
    data object Idle : FileListState()
    class Empty(val text : AnnotatedString) : FileListState()
    class Loading(val text : AnnotatedString) : FileListState()
    class Data(val musicDBList : List<MusicDB>, val totalSize : Int) : FileListState()
}
