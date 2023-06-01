package com.example.expensemanager.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        DBCategories::class,
        DBTypes::class,
        DBFinances::class
    ]
)
abstract class Database : RoomDatabase() {

    abstract fun getDao(): Dao
}