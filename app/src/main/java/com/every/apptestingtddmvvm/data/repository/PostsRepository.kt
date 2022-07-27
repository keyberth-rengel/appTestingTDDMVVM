package com.every.apptestingtddmvvm.data.repository

import com.every.apptestingtddmvvm.application.AppConstants.SUB_URL
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.model.PostData

interface PostsRepository {
    suspend fun getPostDataSource(urlPost: String = SUB_URL): Resource<List<PostData>>
}