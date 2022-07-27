package com.every.apptestingtddmvvm.domain.repository

import com.every.apptestingtddmvvm.application.AppConstants.SUB_URL
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity

interface PostsRepository {

    suspend fun getPostUseCase(urlFeedPosts: String = SUB_URL): Resource<List<PostEntity>>
}