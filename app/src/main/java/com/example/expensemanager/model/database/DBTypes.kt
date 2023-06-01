package com.example.expensemanager.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("types")
data class DBTypes(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "type_name") val name: String
)
