package com.example.expensemanager.model.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dao: Dao) {

    suspend fun insertNewData(dbFinances: DBFinances) {
        withContext(Dispatchers.IO) {
            dao.insertNewData(dbFinances)
        }
    }

    suspend fun getAll(): List<InfoTuple> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getAllData()
        }
    }

    suspend fun removeData(dbFinances: DBFinances) {
        withContext(Dispatchers.IO) {
            dao.deleteData(dbFinances)
        }
    }
}