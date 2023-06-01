package com.capstoneC23PS274.segar.data.preference

import android.content.Context

class UserPreference (context: Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun login(token: String){
        val editor = preferences.edit()
        editor.putString(TOKEN, token)
        editor.putBoolean(ISLOGIN, true)
        editor.apply()
    }

    fun logout(){
        val editor = preferences.edit()
        editor.putString(TOKEN, "")
        editor.putBoolean(ISLOGIN, false)
        editor.apply()
    }

    fun getToken(): String{
        return preferences.getString(TOKEN, "").toString()
    }

    fun checkIsLogin(): Boolean{
        return preferences.getBoolean(ISLOGIN, false)
    }

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val TOKEN = "token"
        private const val ISLOGIN = "islogin"
    }
}