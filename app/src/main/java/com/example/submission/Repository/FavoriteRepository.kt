package com.example.submission.Repository

import androidx.lifecycle.LiveData
import com.example.submission.Data.ResultsItem
import com.example.submission.Database.FavoriteDAO
import com.example.submission.Model.FavoriteModel

class FavoriteRepository(private val favoriteDAO: FavoriteDAO){

    /**
     * list data from database room
     */
    val readAllData:LiveData<List<FavoriteModel>> = favoriteDAO.readAll()

    /**
     * add data to database room
     */
    suspend fun addFavorite(favoriteModel:FavoriteModel){
        favoriteDAO.addFavorite(favoriteModel)
    }

    suspend fun deleteFavorite(favoriteModel: FavoriteModel){
        favoriteDAO.deleteFavorite(favoriteModel)
    }

    fun checkFavorite(favoriteModel:FavoriteModel){
        return favoriteDAO.checkFavorite(favoriteModel)
    }
}