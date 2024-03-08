package eu.mokrzycki.synthriders.customsongsmanager.extensions

import androidx.core.text.isDigitsOnly
import java.util.regex.Pattern

fun String.getFileIdRegexped(): Int? {
    val regex = Pattern.compile("-")
    val split = split(regex, 2).firstOrNull()

    return split?.let {
        if (it.isDigitsOnly()) {
            it.toInt()
        } else {
            null
        }
    }
}