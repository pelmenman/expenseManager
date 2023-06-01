package com.example.expensemanager.model.database

import androidx.room.*

@Entity(
    tableName = "finances",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = DBTypes::class,
            parentColumns = ["id"],
            childColumns = ["type_id"]
        ),
        ForeignKey(
            entity = DBCategories::class,
            parentColumns = ["id"],
            childColumns = ["category_id"]
        )
    ]
)
data class DBFinances(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "type_id") val typeId: Long,
    @ColumnInfo(name = "category_id") val categoryId: Long,
    val date: String,
    val cost: Double
)
