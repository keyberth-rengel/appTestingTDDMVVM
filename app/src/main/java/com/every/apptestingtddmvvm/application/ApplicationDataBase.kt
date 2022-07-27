package com.every.apptestingtddmvvm.application

import android.app.Application
import androidx.room.Room
import com.every.apptestingtddmvvm.application.AppConstants.DATABASE_NAME
import com.every.apptestingtddmvvm.core.SharedPrefe
import com.every.apptestingtddmvvm.data.local.DataBase.AppDataBase

class ApplicationDataBase : Application() {

    companion object {
        lateinit var appDatabase: AppDataBase
        lateinit var appPrefs: SharedPrefe

    }

    override fun onCreate() {
        super.onCreate()

        appPrefs = SharedPrefe(applicationContext)

        appDatabase = Room.databaseBuilder(
            this,
            AppDataBase::class.java, DATABASE_NAME
        ).build()
    }
}