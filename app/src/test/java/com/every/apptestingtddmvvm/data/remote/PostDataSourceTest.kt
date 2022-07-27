package com.every.apptestingtddmvvm.data.remote

import com.every.apptestingtddmvvm.application.AppConstants.SUB_URL
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.model.PostData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class PostDataSourceTest {

    private val urlPosts = SUB_URL
    private val urlPostsSearchInc = "$SUB_URL==="

    @Test()
    fun getPosts_success_returnListPosts() {

        runBlocking {
            val call =
                PostsDataSourceRemote().getPostDataSource(urlPost = urlPosts)

            when (call) {
                is Resource.Success -> {
                    assertTrue(call.data.isEmpty() || call.data.isNotEmpty())
                }
                else -> assertTrue(false)
            }
        }
    }

    @Test
    fun getPosts_paramsFeeds_returnFailure() {
        runBlocking {
            val call: Resource<List<PostData>> =
                PostsDataSourceRemote().getPostDataSource(urlPostsSearchInc)

            when (call) {
                is Resource.Failure -> {
                    assertEquals("Error get posts", call.exception.message)
                }
                else -> assertTrue(false)
            }
        }
    }
}