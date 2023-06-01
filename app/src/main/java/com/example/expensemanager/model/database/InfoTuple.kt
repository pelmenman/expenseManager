package com.example.expensemanager.model.database

import androidx.room.ColumnInfo

data class InfoTuple(
    val id: Long,
    val name: String,
    @ColumnInfo(name = "type_name") val type: String,
    @ColumnInfo(name = "category_name") val category: String,
    val date: String,
    val cost: Double
)
