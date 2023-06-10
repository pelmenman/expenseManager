package com.example.expensemanager.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FinanceDao {

    @Query("SELECT * FROM finance")
    fun getAll(): LiveData<List<Finance>>

    @Query("SELECT sum(cost) FROM finance WHERE date LIKE '%'||:monthYear||'%' ")
    fun getBudget(monthYear : String): LiveData<String>

    @Query("INSERT OR REPLACE INTO finance (type, category, date, cost)" +
    "VALUES(:type, :category, :date, :cost)")
    suspend fun insert(type : String, category : String, date : String, cost : Double)

    @Delete
    suspend fun delete(finance: Finance)
}