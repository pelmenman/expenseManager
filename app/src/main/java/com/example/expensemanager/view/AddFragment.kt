package com.example.expensemanager.view
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.expensemanager.R
import com.example.expensemanager.database.Finance
import com.example.expensemanager.database.FinanceDao
import com.example.expensemanager.databinding.FragmentAddBinding
import com.example.expensemanager.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import kotlin.math.abs


@AndroidEntryPoint
class AddFragment @Inject constructor(val dao : FinanceDao) : Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var calendar : Calendar
    private var expenses = arrayOf("Еда", "Транспорт", "Развлечения", "Одежда", "Дом", "Спорт", "Транспорт", "Другое")
    private var incomes = arrayOf("Зарплата", "Аренда", "Аванс", "Другое")
    private lateinit var categoryDialogFragmentBuilder : AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        calendar = Calendar.getInstance()
        categoryDialogFragmentBuilder = AlertDialog.Builder(context)
        changeItem(expenses, categoryDialogFragmentBuilder)

        binding.switchType.setOnClickListener {
            if (binding.switchType.isChecked) {
                binding.textViewType.text = "Доход"
                changeItem(incomes, categoryDialogFragmentBuilder)
            } else {
                binding.textViewType.text = "Расход"
                changeItem(expenses, categoryDialogFragmentBuilder)
            }
            if(binding.buttonCategory.text.isNotEmpty()) binding.buttonCategory.text = ""
        }

        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate(calendar)
        }

        binding.buttonDate.setOnClickListener {
            DatePickerDialog(this.requireContext(),
            datePicker,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()
        }


        binding.buttonCategory.setOnClickListener {
            categoryDialogFragmentBuilder.create().show()
        }

        binding.buttonAdd.setOnClickListener{
            if(checkFieldsCorrect()) {
                Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            } else {

                mainViewModel.insert(
                    Finance(0,
                     binding.textViewType.text.toString(),
                        binding.buttonCategory.text.toString(),
                     binding.buttonDate.text.toString(),
                     costType()
                )
                )
                Toast.makeText(context, "Запись добавлена", Toast.LENGTH_SHORT).show()
                cleanAll()
            }
        }

        return binding.root
    }

    private fun costType(): Double =
        if(binding.switchType.isChecked) {
            abs(binding.editTextCost.text.toString().toDouble())
        } else {
            -1 * binding.editTextCost.text.toString().toDouble()
        }



    private fun cleanAll() {
        binding.editTextCost.text.clear()
        binding.textViewType.text = "Расход"
        binding.switchType.isChecked = false
        binding.buttonDate.text = ""
        binding.buttonCategory.text = ""
    }

    private fun checkFieldsCorrect(): Boolean {
        //1. No cost
        //2. No date
        //3. No category
        return (binding.editTextCost.text.isEmpty() || binding.buttonDate.text.isEmpty() || binding.buttonCategory.text.isEmpty())
    }

    private fun changeItem(itemsArray: Array<String>, categoryDialogFragmentBuilder: AlertDialog.Builder) {
        categoryDialogFragmentBuilder.setItems(itemsArray) { _, which -> binding.buttonCategory.text = itemsArray[which]
        }
    }

    private fun updateDate(calendar: Calendar) {
        val format = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(format, Locale.UK)
        binding.buttonDate.text = sdf.format(calendar.time)
    }

}