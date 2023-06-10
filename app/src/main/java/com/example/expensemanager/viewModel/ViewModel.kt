package com.example.expensemanager.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.database.Finance
import com.example.expensemanager.database.FinanceDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(val dao : FinanceDao): ViewModel() {
    val liveData : LiveData<List<Finance>> = dao.getAll()

    fun insert(finance: Finance) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(finance)
        }
    }

    fun getBudgetOf(monthYear : String): LiveData<String> = dao.getBudget(monthYear)

    fun getBudgetOf(): LiveData<String> = dao.getBudget()


}