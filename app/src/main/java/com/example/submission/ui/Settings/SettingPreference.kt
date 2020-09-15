package com.example.submission.ui.Settings

import android.content.Context
import android.content.SharedPreferences
import androidx.core.os.bundleOf

class SettingPreference {

    companion object{
        private const val PREFS_NAME = "setting_pref"
        private const val DAILY_REMINDER = "isDaily"
    }
    private lateinit var sharedPreference: SharedPreferences

    fun SettingPreference(context: Context){
        sharedPreference = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
    }

    fun setDailyReminder(isActive:Boolean){
        val editor:SharedPreferences.Editor = sharedPreference.edit()
        editor.putBoolean(DAILY_REMINDER,isActive)
        editor.apply()
    }

    fun getDailyReminder():Boolean{
        return sharedPreference.getBoolean(DAILY_REMINDER,false)
    }
}