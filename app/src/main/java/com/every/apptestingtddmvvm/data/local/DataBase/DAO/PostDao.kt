package com.every.apptestingtddmvvm.data.local.DataBase.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity")
    fun getAllPosts(): List<PostEntity>

    @Insert
    fun insertPost(post: PostEntity)

    @Delete
    fun deletePosts(vararg posts: PostEntity)
}