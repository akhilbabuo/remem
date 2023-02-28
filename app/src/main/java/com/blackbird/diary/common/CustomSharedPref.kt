package com.blackbird.diary.common

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.blackbird.diary.application.App

object CustomSharedPref {

    @Synchronized
    private fun getPreference():SharedPreferences{
        return App.appContext.getSharedPreferences(AppConstants.pref_name,Context.MODE_PRIVATE)
    }


    fun savePin(pin:String){
        getPreference().edit {
            putString(AppConstants.pin_key,pin)
        }
    }

    fun getPin():String?{
        return  getPreference().getString(AppConstants.pin_key,"")
    }

    fun isPinSet():Boolean{
        return getPreference().getBoolean(AppConstants.isPinSetKey,false)
    }
    fun setIsPinSet(isPinSetKey:Boolean){
        getPreference().edit {
            putBoolean(AppConstants.isPinSetKey,isPinSetKey)
        }
    }

}