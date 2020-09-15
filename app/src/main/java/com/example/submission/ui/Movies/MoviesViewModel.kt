package com.example.submission.ui.Movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission.Data.MovieResponse
import com.example.submission.Data.ResultsItem
import com.example.submission.Network.Api
import com.example.submission.Network.RetrofitClient
import com.example.submission.Repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
//class MoviesViewModel : ViewModel() {
class MoviesViewModel(private val repository: MoviesRepository):ViewModel(){

    val liveMovies = MutableLiveData<ArrayList<ResultsItem>>()
//    val liveSearchMovies = MutableLiveData<ArrayList<ResultsItem>>()

//    fun setMoviesData(){
//
//        val api: Api = RetrofitClient.api
//        val call:Call<MovieResponse> = api.getData()
//
//        call.enqueue(object :Callback<MovieResponse>{
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                Log.d("ApiError: ",t.message)
//            }
//
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                liveMovies.value = response.body()?.results as ArrayList<ResultsItem>
//            }
//        })
//    }
//
//    fun getMoviesData(): LiveData<ArrayList<ResultsItem>>{
//        return liveMovies
//    }

    fun getMoviesData():LiveData<ArrayList<ResultsItem>>{
        viewModelScope.launch (Dispatchers.IO) {
            val responseRepository = repository.getMoviesData()
            responseRepository.enqueue(object :Callback<MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    liveMovies.value = response.body()?.results as ArrayList<ResultsItem>
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("ApiFailure",t.message)
                }
            })
        }

        return liveMovies
    }

//    fun setSearchMoviesData(title:String){
//        val api:Api = RetrofitClient.api
//        val callSearch:Call<MovieResponse> = api.getSearchData(title)
//
//        callSearch.enqueue(object :Callback<MovieResponse>{
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                Log.d("ApiSearchError", t.message)
//            }
//
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                liveSearchMovies.value = response.body()?.results as ArrayList<ResultsItem>
//            }
//        })
//    }
//
//    fun getSearchMoviesData(): LiveData<ArrayList<ResultsItem>>{
//        return liveSearchMovies
//    }

    fun getSearchMoviesData(title:String):LiveData<ArrayList<ResultsItem>>{
        viewModelScope.launch (Dispatchers.IO){
            val responseRepository = repository.getSearchMoviesData(title)
            responseRepository.enqueue(object :Callback<MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    liveMovies.value = response.body()?.results as ArrayList<ResultsItem>
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("ApiFailure",t.message)
                }
            })
        }

        return liveMovies
    }
}