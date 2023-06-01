package com.example.expensemanager.model.database

data class Finance(
    val name: String,
    val typeId: Long,
    val categoryId: Long,
    val date: String,
    val cost: Double
) {
    fun toDbEntity(): DBFinances = DBFinances(
        id = 0,
        name = name,
        typeId = typeId,
        categoryId = categoryId,
        date = date,
        cost = cost
    )
}
