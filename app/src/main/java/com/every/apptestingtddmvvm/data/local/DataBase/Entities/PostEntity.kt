package com.every.apptestingtddmvvm.data.local.DataBase.Entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PostEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    @ColumnInfo(name = "adult") val adult: Boolean?,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double?,
) : Parcelable