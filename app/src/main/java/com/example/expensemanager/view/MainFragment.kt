@file:JvmName("MainFragment")
package com.example.expensemanager.view
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.example.expensemanager.R
import com.example.expensemanager.database.FinanceDao
import com.example.expensemanager.databinding.FragmentMainBinding
import com.example.expensemanager.viewModel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observer
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment @Inject constructor(val dao : FinanceDao): Fragment(R.layout.fragment_main) {
    private lateinit var calendar : Calendar
    private lateinit var binding: FragmentMainBinding
    private val viewModel : ViewModel by viewModels()
    private lateinit var observer : Observer<LiveData<String>>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        calendar = Calendar.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    private fun setMonthYear(): String {
        val months = arrayOf("Январь", "Февраль", "Март", "Апрель", "Май",
        "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь")

        return months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR)
    }
}