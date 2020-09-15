package com.example.submission.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submission.Model.FavoriteModel

@Database(entities = [FavoriteModel::class],version = 1, exportSchema = false)

abstract class FavoriteDatabase:RoomDatabase(){

    abstract fun favoriteDAO():FavoriteDAO

    companion object{
        @Volatile
        private var INSTANCE:FavoriteDatabase? = null

        fun getDatabase(context: Context):FavoriteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorite_database"
                ).build()
            INSTANCE = instance
            return instance
            }
        }
    }
}