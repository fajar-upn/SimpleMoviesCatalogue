package com.example.submission.Network

import com.example.submission.Data.MovieResponse
import com.example.submission.Data.ResultsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api{

    companion object{
        const val API_KEY = "YOUR_API_HERE"
    }

    @GET("movie/upcoming?api_key=${API_KEY}&language=en-US&page=1")
    fun getData():Call<MovieResponse>

    @GET("search/movie?api_key=${API_KEY}&language=en-US&page=1")
    fun getSearchData(@Query("query") title:String):Call<MovieResponse>
}