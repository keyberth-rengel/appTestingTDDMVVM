package com.every.apptestingtddmvvm.data.model

import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("id") val id: Long,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
)

data class PostsBodyResult(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<PostData>,
)
