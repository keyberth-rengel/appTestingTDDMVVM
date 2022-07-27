package com.every.apptestingtddmvvm.data.remote

import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.model.PostData
import com.every.apptestingtddmvvm.data.remote.services.ApiServices
import com.every.apptestingtddmvvm.data.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception

class PostsDataSourceRemote : PostsRepository {

    override suspend fun getPostDataSource(urlPost: String): Resource<List<PostData>> {
        return try {
            val call = ApiServices.retrofitServices.getPosts(urlPost)
            val response = call.body()

            return if (call.isSuccessful && response != null) {
                Resource.Success(response.results)
            } else {
                Resource.Failure(Exception("Error get posts"))
            }

        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}