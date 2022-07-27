package com.every.apptestingtddmvvm.data.local

import com.every.apptestingtddmvvm.application.ApplicationDataBase
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity
import com.every.apptestingtddmvvm.data.repository.FavoritePostRepository
import com.every.apptestingtddmvvm.data.repository.InsertFavoritePostRepository

class FavoritePostsDataSourceLocal : FavoritePostRepository, InsertFavoritePostRepository {
    override suspend fun getFavoriteDataSourceLocal(): Resource<List<FavoriteEntity>> {
        return try {
            val result = ApplicationDataBase.appDatabase.favoriteDao().getAllFavorites()
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure(Exception("Error get favorites"))
        }
    }

    override suspend fun getPostByIdDataSource(postId: Long): Resource<FavoriteEntity?> {
        return try {
            val res = ApplicationDataBase.appDatabase.favoriteDao().findById(postId)
            Resource.Success(res)
        } catch (e: Exception) {
            Resource.Failure(Exception("Error find post by uid"))
        }
    }

    override suspend fun insertPostDataSource(post: FavoriteEntity): Resource<Unit> {
        return try {
            val res = ApplicationDataBase.appDatabase.favoriteDao().insertFavorite(post)
            Resource.Success(res)
        } catch (e: Exception) {
            Resource.Failure(Exception("Error insert post"))
        }
    }
}