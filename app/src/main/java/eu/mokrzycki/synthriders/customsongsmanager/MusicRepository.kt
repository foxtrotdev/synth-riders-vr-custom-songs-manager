package eu.mokrzycki.synthriders.customsongsmanager

import eu.mokrzycki.synthriders.customsongsmanager.db.MusicDB
import eu.mokrzycki.synthriders.customsongsmanager.db.MusicDao

class MusicRepository(private val musicDao: MusicDao) {

    suspend fun insert(musicDB: MusicDB) {
        musicDao.insertAll(musicDB)
    }

    suspend fun insertAll(musicDBList: List<MusicDB>) {
        musicDao.insertAll(*musicDBList.toTypedArray())
    }

    suspend fun getAll(): List<MusicDB> {
        return musicDao.getAll()
    }

    suspend fun getAllByIds(ids: List<Int>): List<MusicDB> {
        return musicDao.getAllByIds(ids)
    }

    fun existsInDatabase(musicDB: MusicDB): Boolean {
        return musicDao.exists(musicDB.id) > 0
    }

    fun existsInDatabase(id: Int): Boolean {
        return musicDao.exists(id) > 0
    }
}