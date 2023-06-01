package com.example.expensemanager.model.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="categories")
data class DBCategories(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "category_name")val name: String
    )
