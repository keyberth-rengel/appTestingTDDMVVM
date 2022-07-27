package com.every.apptestingtddmvvm.data.local.DataBase.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM FavoriteEntity")
    fun getAllFavorites(): List<FavoriteEntity>

    @Query("SELECT * FROM FavoriteEntity WHERE id LIKE :id LIMIT 1")
    fun findById(id: Long): FavoriteEntity

    @Insert
    fun insertFavorite(post: FavoriteEntity)

    @Delete
    fun deleteFavorite(post: FavoriteEntity)
}