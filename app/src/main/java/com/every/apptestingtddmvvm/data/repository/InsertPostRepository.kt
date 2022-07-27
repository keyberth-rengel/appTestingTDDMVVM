package com.every.apptestingtddmvvm.data.repository

import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity

interface InsertPostRepository {
    suspend fun insertPostDataSource(post: PostEntity): Resource<Unit>
}