package com.example.submission.Repository

import com.example.submission.Data.MovieResponse
import com.example.submission.Network.RetrofitClient
import retrofit2.Call

class MoviesRepository {
    suspend fun getMoviesData():Call<MovieResponse>{
        return RetrofitClient.api.getData()
    }

    suspend fun getSearchMoviesData(title:String):Call<MovieResponse> {
        return RetrofitClient.api.getSearchData(title)
    }
}