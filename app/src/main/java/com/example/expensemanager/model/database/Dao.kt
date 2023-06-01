package com.example.expensemanager.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert(entity = DBFinances::class)
    fun insertNewData(finance:DBFinances)

    @Query("SELECT finances.id, finances.name, type_name, category_name, finances.date, finances.cost FROM finances\n" +
    "INNER JOIN types ON types.id = finances.type_id\n" +
    "INNER JOIN categories ON categories.id = finances.category_id\n")
    fun getAllData(): List<InfoTuple>

    @Delete
    fun deleteData(finance:DBFinances)

//    @Query("DELETE FROM finances WHERE id = :id")
//    fun deleteDataById(id:Long)
}