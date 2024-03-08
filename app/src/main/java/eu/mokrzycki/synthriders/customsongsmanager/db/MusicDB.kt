package eu.mokrzycki.synthriders.customsongsmanager.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class MusicDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val artist: String,
    val difficulties: List<String>,
    val duration: String,
    val coverUrl : String?
){
    companion object{
        fun mock() = MusicDB(0, "title", "artist", listOf("Empty"), "01:01", "/api/beatmaps/7998/coverUrl")
    }
}