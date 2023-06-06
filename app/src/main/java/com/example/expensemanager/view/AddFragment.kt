package com.example.expensemanager.view
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expensemanager.R
import com.example.expensemanager.databinding.FragmentAddBinding
import com.example.expensemanager.viewModel.AddViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddFragment : Fragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var observer : Observer<String>
    private lateinit var viewModel: AddViewModel
    private lateinit var calendar : Calendar
    private var expenses = arrayOf("Еда", "Транспорт", "Развлечения", "Одежда", "Дом", "Спорт", "Транспорт", "Другое")
    private var incomes = arrayOf("Зарплата", "Аренда", "Аванс", "Другое")
    private lateinit var categoryDialogFragmentBuilder : AlertDialog.Builder

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        calendar = Calendar.getInstance()
        categoryDialogFragmentBuilder = AlertDialog.Builder(context)
        changeItem(expenses, categoryDialogFragmentBuilder)

        //val builder = AlertDialog.Builder(requireContext())
        observer = Observer {
            //Toast.makeText(activity, "Мы добавили!!!!", Toast.LENGTH_SHORT).show()
        }

        viewModel = ViewModelProvider(this)[AddViewModel :: class.java]

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
                Toast.makeText(context, binding.buttonCategory.text, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun checkFieldsCorrect(): Boolean {
        //1. No cost
        //2. No date
        //3. No category
        return (binding.editTextCost.text.isEmpty() || binding.buttonDate.text.isEmpty() || binding.buttonCategory.text.isEmpty())
    }

    private fun changeItem(itemsArray: Array<String>, categoryDialogFragmentBuilder: AlertDialog.Builder) {
        categoryDialogFragmentBuilder.setItems(itemsArray, DialogInterface.OnClickListener { _, which ->
            binding.buttonCategory.text = itemsArray[which]

        })
    }

    private fun updateDate(calendar: Calendar) {
        val format = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(format, Locale.UK)
        binding.buttonDate.text = sdf.format(calendar.time)
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe(this, observer)
    }

    override fun onStop() {
        super.onStop()
        viewModel.liveData.removeObserver(observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}