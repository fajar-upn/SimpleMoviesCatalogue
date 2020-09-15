package com.example.submission.Network

import com.example.submission.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient{


    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val retrofit by lazy {
       Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
    }

    val api:Api by lazy{
        retrofit.create(Api::class.java)
    }


}