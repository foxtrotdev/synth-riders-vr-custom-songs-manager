package eu.mokrzycki.synthriders.customsongsmanager.extensions

import eu.mokrzycki.synthriders.customsongsmanager.RetrofitBuilder
import eu.mokrzycki.synthriders.customsongsmanager.api.beatmaps.id.MusicResponse
import eu.mokrzycki.synthriders.customsongsmanager.db.MusicDB

fun MusicResponse.toMusicDB(): MusicDB {
    return MusicDB(
        id = id,
        title = title,
        artist = artist,
        difficulties = difficulties,
        duration = duration,
        coverUrl = coverUrl?.let { RetrofitBuilder.BASE_URL + it}
    )
}