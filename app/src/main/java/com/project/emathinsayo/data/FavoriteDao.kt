package com.project.emathinsayo.data

import androidx.room.Dao
import androidx.room.Query
import com.project.emathinsayo.common.BaseDao
import com.project.emathinsayo.data.entity.Favorites
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao : BaseDao<Favorites> {

    @Query("SELECT * FROM $TABLE_NAME")
    fun all(): Flow<List<Favorites>>

    @Query("SELECT title FROM $TABLE_NAME")
    fun allIds(): Flow<List<String>>

    @Query("SELECT * FROM $TABLE_NAME WHERE bookId = :id")
    fun get(id: Int): Favorites?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY bookId DESC LIMIT 1")
    fun getFirst(): Flow<Favorites?>

    @Query("DELETE FROM $TABLE_NAME WHERE title = :title")
    suspend fun deleteByTitle(title: String)

    @Query("DELETE FROM $TABLE_NAME")
    fun nukeTable()

    companion object {
        const val TABLE_NAME = "Favorites"
    }
}