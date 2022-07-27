package com.every.apptestingtddmvvm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.domain.PostsUseCase
import kotlinx.coroutines.Dispatchers

class PostViewModel( private val postsUseCase: PostsUseCase ): ViewModel() {

    fun getFeedPosts() = liveData(Dispatchers.IO) {

        emit(Resource.Loading)
        try {
            emit(postsUseCase.getPostUseCase())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class PostViewModelFactory(private val repo: PostsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PostsUseCase::class.java).newInstance(repo)
    }
}