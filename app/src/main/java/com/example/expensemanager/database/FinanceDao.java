package com.example.expensemanager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FinanceDao {

    @Query("SELECT * FROM Finance")
    public LiveData<List<Finance>> getAll();

    @Query("SELECT sum(cost) FROM Finance WHERE date LIKE '%'||:monthYear||'%' ")
    public LiveData<String> getBudget(String monthYear);


    @Insert
    public void insert(Finance finance);

    @Delete
    public void delete(Finance finance);
}
