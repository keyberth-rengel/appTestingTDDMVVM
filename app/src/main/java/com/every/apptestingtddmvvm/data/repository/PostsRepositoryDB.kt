package com.every.apptestingtddmvvm.data.repository

import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity

interface PostsRepositoryDB {
    suspend fun getFeedPostDataSource(): Resource<List<PostEntity>>
}