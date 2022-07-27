package com.every.apptestingtddmvvm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity
import com.every.apptestingtddmvvm.domain.FavoritePostsUseCase
import kotlinx.coroutines.Dispatchers

class FavoritePostViewModel(private val favoritePostsUseCase: FavoritePostsUseCase) : ViewModel() {

    fun getFavoritePosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(favoritePostsUseCase.getFavoritePostsUseCase())

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun insertFavoritePost(post: FavoriteEntity) = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(favoritePostsUseCase.insertFavoritePostUseCase(post))

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}


class FavoritePostViewModelFactory(private val repo: FavoritePostsUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FavoritePostsUseCase::class.java).newInstance(repo)
    }
}