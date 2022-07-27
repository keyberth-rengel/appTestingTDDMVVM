package com.every.apptestingtddmvvm.core

import android.content.Context
import android.content.SharedPreferences

class SharedPrefe(val context: Context) {

    private val PREFERENCE_DB_KEY = "preference_DB_key"
    private val SAVED_SESSION_USER_USERNAME = "saved_session_user_username"


    private val sharedPrefe: SharedPreferences = context.getSharedPreferences(PREFERENCE_DB_KEY, 0)

    fun setSession(username: String) {
        sharedPrefe.edit().putString(SAVED_SESSION_USER_USERNAME, username).apply()
    }

    fun getSession(): String {
        return sharedPrefe.getString(SAVED_SESSION_USER_USERNAME, "")!!
    }

    fun wipe() {
        sharedPrefe.edit().clear().apply()
    }
}