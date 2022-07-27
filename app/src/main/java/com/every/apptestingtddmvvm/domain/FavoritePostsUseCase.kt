package com.every.apptestingtddmvvm.domain

import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity
import com.every.apptestingtddmvvm.data.local.FavoritePostsDataSourceLocal
import com.every.apptestingtddmvvm.domain.repository.FavoritePostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePostsUseCase(private val favoritePost: FavoritePostsDataSourceLocal) :
    FavoritePostRepository {
    override suspend fun getFavoritePostsUseCase(): Resource<List<FavoriteEntity>> {
        return favoritePost.getFavoriteDataSourceLocal()
    }

    override suspend fun insertFavoritePostUseCase(post: FavoriteEntity): Resource<Unit> {

        val postExist = favoritePost.getPostByIdDataSource(post.id)

        return when (postExist) {
            is Resource.Failure -> {
                favoritePost.insertPostDataSource(post)
            }
            is Resource.Success -> {
                withContext(Dispatchers.IO){
                    if (postExist.data == null) {
                        favoritePost.insertPostDataSource(post)
                    } else {
                        Resource.Success(Unit)
                    }
                }
            }
            else -> Resource.Success(Unit)
        }
    }
}