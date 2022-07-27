package com.every.apptestingtddmvvm.domain

import com.every.apptestingtddmvvm.application.ApplicationDataBase
import com.every.apptestingtddmvvm.core.InternetCheck
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity
import com.every.apptestingtddmvvm.data.local.PostsDataSourceLocal
import com.every.apptestingtddmvvm.data.remote.PostsDataSourceRemote
import com.every.apptestingtddmvvm.domain.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsUseCase(
    private val postsDataSourceRemote: PostsDataSourceRemote,
    private val postsDataSourceLocal: PostsDataSourceLocal
) : PostsRepository {
    override suspend fun getPostUseCase(urlFeedPosts: String): Resource<List<PostEntity>> {

        return if (InternetCheck.isNetworkAvailable()) {

            val result = postsDataSourceRemote.getPostDataSource(urlFeedPosts)

            when (result) {
                is Resource.Success -> {
                    withContext(Dispatchers.IO) {
                        val posts = ApplicationDataBase.appDatabase.feedDao().getAllPosts()
                        ApplicationDataBase.appDatabase.feedDao()
                            .deletePosts(posts = posts.toTypedArray())
                        result.data.forEach {
                            val itemDB = PostEntity(
                               adult = it.adult,
                                backdropPath = it.backdropPath,
                                id = it.id,
                                originalTitle = it.originalTitle,
                                overview = it.overview,
                                posterPath = it.posterPath,
                                releaseDate = it.releaseDate,
                                voteAverage = it.voteAverage,
                            )
                            postsDataSourceLocal.insertPostDataSource(itemDB)
                        }

                    }
                    postsDataSourceLocal.getFeedPostDataSource()
                }
                else -> Resource.Failure(Exception("Error get posts"))
            }

        } else {
            postsDataSourceLocal.getFeedPostDataSource()
        }
    }
}