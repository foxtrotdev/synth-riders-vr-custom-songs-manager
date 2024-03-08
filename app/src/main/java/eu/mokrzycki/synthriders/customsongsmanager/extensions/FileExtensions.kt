package eu.mokrzycki.synthriders.customsongsmanager.extensions

import java.io.File

/**
 * @return List of files that are not directories.
 * In worst scenario it will return an empty list.
 */

const val SYNTH_ENABLED = "synth"
const val SYNTH_DISABLED = "synth_disabled"

fun File.listFilesNotDirectories(): List<File> {
    return listFiles().orEmpty().filter { it.isFile }
}

fun File.enableExtension() {
    if (extension != SYNTH_ENABLED) {
        val newFileName = "$nameWithoutExtension.$SYNTH_ENABLED"
        this.renameTo(File(parent, newFileName))
    }
}

fun File.disableExtension() {
    if (extension != SYNTH_DISABLED) {
        val newFileName = "$nameWithoutExtension.$SYNTH_DISABLED"
        this.renameTo(File(parent, newFileName))
    }
}