package eu.mokrzycki.synthriders.customsongsmanager.observers

import android.os.FileObserver
import java.io.File

class CustomSongsObserver(
    file: File,
    val onEventBlock: (
        event: Int,
        path: String?,
    ) -> Unit,
) : FileObserver(file) {
    override fun onEvent(event: Int, path: String?) {
        onEventBlock(event, path)
    }
}