package com.every.apptestingtddmvvm.data.repository

import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity

interface InsertFavoritePostRepository {
    suspend fun insertPostDataSource(post: FavoriteEntity): Resource<Unit>
}