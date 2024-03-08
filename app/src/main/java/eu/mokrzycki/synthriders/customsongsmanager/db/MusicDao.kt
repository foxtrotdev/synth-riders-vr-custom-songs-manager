package eu.mokrzycki.synthriders.customsongsmanager.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MusicDao {
    @Query("SELECT * FROM music")
    suspend fun getAll(): List<MusicDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg musicDBS: MusicDB)

    @Query("SELECT COUNT(*) FROM music WHERE id = :id")
    fun exists(id: Int): Int

    @Query("SELECT * FROM music WHERE id IN (:ids)")
    suspend fun getAllByIds(ids: List<Int>): List<MusicDB>
}