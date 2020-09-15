package com.example.submission.ui.Favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.submission.Data.ResultsItem
import com.example.submission.Database.FavoriteDatabase
import com.example.submission.Model.FavoriteModel
import com.example.submission.Repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    application: Application
) : AndroidViewModel(application) {

    val readAllData:LiveData<List<FavoriteModel>>
    private var favoriteRepository:FavoriteRepository

    init {
        val favoriteDAO = FavoriteDatabase.getDatabase(
            application
        ).favoriteDAO()
        favoriteRepository = FavoriteRepository(favoriteDAO)
        readAllData = favoriteRepository.readAllData
    }

    fun addFavorite(favoriteModel: FavoriteModel){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.addFavorite(favoriteModel)
        }
    }

    fun deleteFavorite(favoriteModel: FavoriteModel){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavorite(favoriteModel)
        }
    }

    fun checkFavorite(favoriteModel: FavoriteModel){
        return favoriteRepository.checkFavorite(favoriteModel)
    }


}