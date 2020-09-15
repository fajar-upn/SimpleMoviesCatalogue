package com.example.submission.Database

import android.content.ContentValues
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.submission.Model.FavoriteModel

@Dao
interface FavoriteDAO{

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun readAll():LiveData<List<FavoriteModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favoriteModel: FavoriteModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun checkFavorite(favoriteModel: FavoriteModel)

    @Delete
    suspend fun deleteFavorite(favoriteModel: FavoriteModel)

}