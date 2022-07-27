package com.every.apptestingtddmvvm.data.remote.services

import com.every.apptestingtddmvvm.application.AppConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ApiServices {
    val retrofitServices: APIService by lazy {
        retrofit.create( APIService::class.java)
    }
}
