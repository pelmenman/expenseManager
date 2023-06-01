package com.example.expensemanager.di
import com.example.expensemanager.model.database.Database
import android.content.Context
import androidx.room.Room

object Dependencies {

        private lateinit var applicationContext: Context

        fun init(context: Context) {
            applicationContext = context
        }

        private val appDatabase: Database by lazy {
            Room.databaseBuilder(applicationContext, Database::class.java, "database.db")
                .createFromAsset("room_article.db").build()
        }
    }