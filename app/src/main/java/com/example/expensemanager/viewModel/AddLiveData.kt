package com.example.expensemanager.viewModel

import androidx.lifecycle.LiveData
import com.example.expensemanager.model.Category
import com.example.expensemanager.model.Finance
import com.example.expensemanager.model.Type

class AddLiveData: LiveData<Finance>() {

    fun setData(name : String, type : String, category : String, date : String, cost : Double) {
        value = Finance(name, type, category, date, cost)
    }

}