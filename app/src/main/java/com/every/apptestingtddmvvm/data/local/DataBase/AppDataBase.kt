package com.every.apptestingtddmvvm.data.local.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.every.apptestingtddmvvm.data.local.DataBase.DAO.FavoriteDao
import com.every.apptestingtddmvvm.data.local.DataBase.DAO.PostDao
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity

@Database(entities = [ PostEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun feedDao(): PostDao
    abstract fun favoriteDao(): FavoriteDao
}