package com.every.apptestingtddmvvm.domain.repository

import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity

interface FavoritePostRepository {
    suspend fun getFavoritePostsUseCase(): Resource<List<FavoriteEntity>>
    suspend fun insertFavoritePostUseCase(post: FavoriteEntity): Resource<Unit>
}