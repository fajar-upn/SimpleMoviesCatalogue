package com.example.submission.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_table")
data class FavoriteModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val id_movies:Int? = null,
    val title:String,
    val language:String,
    val overview:String,
    val poster_path:String,
    val backdrop_path:String,
    val vote_averege:String,
    val release_date: String
):Parcelable