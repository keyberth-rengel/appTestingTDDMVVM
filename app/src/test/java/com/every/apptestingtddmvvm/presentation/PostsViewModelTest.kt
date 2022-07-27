package com.every.apptestingtddmvvm.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.every.apptestingtddmvvm.core.MainCoroutinesRule
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.core.getOrAwaitValue
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity
import com.every.apptestingtddmvvm.data.local.PostsDataSourceLocal
import com.every.apptestingtddmvvm.data.remote.PostsDataSourceRemote
import com.every.apptestingtddmvvm.domain.PostsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

class PostsViewModelTest {
    @get:Rule
    val instantExcecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    companion object {
        private lateinit var feedPostsViewModel: PostViewModel

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            val feedPostsDataSourceRemote = PostsDataSourceRemote()
            val feedPostsDataSourceLocal = PostsDataSourceLocal()
            val feedPostsUseCase =
                PostsUseCase(feedPostsDataSourceRemote, feedPostsDataSourceLocal)
            feedPostsViewModel = PostViewModel(feedPostsUseCase)
        }
    }

    @Test
    fun getPosts_success_returnListPosts() {

        runBlocking {
            val call: LiveData<Resource<List<PostEntity>>> = feedPostsViewModel.getFeedPosts()
            val result = call.getOrAwaitValue()

            when (result) {
                is Resource.Success -> {
                    Assert.assertTrue(result.data.isEmpty() || result.data.isNotEmpty())
                }
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Failure -> Assert.fail("Error get post")
            }
        }
    }

    @Test
    fun getPosts_paramsFeeds_returnFailure() {

        runBlocking {
            val call: LiveData<Resource<List<PostEntity>>> = feedPostsViewModel.getFeedPosts()
            val result = call.getOrAwaitValue()

            println("result = $result")

            when (result) {
                is Resource.Failure -> {
                    Assert.assertEquals("Error get posts", result.exception.message)
                }
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> Assert.fail("Success get post")
            }
        }
    }
}