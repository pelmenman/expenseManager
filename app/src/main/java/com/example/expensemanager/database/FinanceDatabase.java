package com.example.expensemanager.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Finance.class}, version = 1)
public abstract class FinanceDatabase extends RoomDatabase {
    public abstract FinanceDao getDao();
}
