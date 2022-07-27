package com.every.apptestingtddmvvm.data.repository

import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity

interface FavoritePostRepository {
    suspend fun getFavoriteDataSourceLocal() : Resource<List<FavoriteEntity>>
    suspend fun getPostByIdDataSource(postId: Long): Resource<FavoriteEntity?>
}