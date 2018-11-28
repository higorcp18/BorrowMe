package com.borrowme.Aplication

import android.app.Application
import android.arch.persistence.room.Room
import com.borrowme.Database.AppDataBase

open class MyApplication : Application() {

    companion object {
        var database: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        //Room
        database = Room.databaseBuilder(this, AppDataBase::class.java, "my-db").allowMainThreadQueries().build()
    }
}