package com.every.apptestingtddmvvm.domain

import com.every.apptestingtddmvvm.application.AppConstants.SUB_URL
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.remote.PostsDataSourceRemote
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class PostsUseCaseTest {

    private val urlPosts = SUB_URL
    private val urlPostsSearchInc = "${SUB_URL}query=mobile"

    @Test
    fun getPosts_success_returnListPosts() {

        runBlocking {
            val call =
                PostsDataSourceRemote().getPostDataSource(urlPosts)

            when (call) {
                is Resource.Success -> {
                    Assert.assertTrue(call.data.isEmpty() || call.data.isNotEmpty())
                }
                else -> Assert.assertTrue(false)
            }
        }
    }

    @Test
    fun getPosts_paramsFeeds_returnFailure() {

        runBlocking {
            val call =
                PostsDataSourceRemote().getPostDataSource(urlPostsSearchInc)

            when (call) {
                is Resource.Failure -> {
                    Assert.assertEquals("Error get posts", call.exception.message)
                }
                else -> Assert.assertTrue(false)
            }
        }
    }
}