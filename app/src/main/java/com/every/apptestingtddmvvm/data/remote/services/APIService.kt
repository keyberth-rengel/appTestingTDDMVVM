package com.every.apptestingtddmvvm.data.remote.services

import com.every.apptestingtddmvvm.application.AppConstants.API_KEY
import com.every.apptestingtddmvvm.application.AppConstants.API_LANGUAGE
import com.every.apptestingtddmvvm.data.model.PostsBodyResult
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET
    suspend fun getPosts(
        @Url url: String = "",
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = API_LANGUAGE
    ): Response<PostsBodyResult>
}