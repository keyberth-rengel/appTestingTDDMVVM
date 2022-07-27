package com.every.apptestingtddmvvm.data.local

import com.every.apptestingtddmvvm.application.ApplicationDataBase
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity
import com.every.apptestingtddmvvm.data.repository.PostsRepositoryDB
import com.every.apptestingtddmvvm.data.repository.InsertPostRepository

class PostsDataSourceLocal : PostsRepositoryDB, InsertPostRepository {

    override suspend fun getFeedPostDataSource(): Resource<List<PostEntity>> {
        return try {
            val res = ApplicationDataBase.appDatabase.feedDao().getAllPosts()
            Resource.Success(res)
        } catch (e: Exception) {
            Resource.Failure(Exception("Error get all feeds"))
        }
    }

    override suspend fun insertPostDataSource(post: PostEntity): Resource<Unit> {
        return try {
            val res = ApplicationDataBase.appDatabase.feedDao().insertPost(post)
            Resource.Success(res)
        } catch (e: Exception) {
            Resource.Failure(Exception("Error insert post"))
        }
    }
}