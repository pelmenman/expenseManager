package com.example.expensemanager.view
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expensemanager.R
import com.example.expensemanager.databinding.FragmentAddBinding
import com.example.expensemanager.model.Finance
import com.example.expensemanager.viewModel.AddViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddFragment : Fragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var observer : Observer<Finance>
    private lateinit var viewModel: AddViewModel
    private lateinit var calendar : Calendar
    private lateinit var categoryDialogFragment: CategoryDialogFragment

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        calendar = Calendar.getInstance()
        categoryDialogFragment = CategoryDialogFragment()

        observer = Observer<Finance> {
            Toast.makeText(activity, "Мы добавили!!!!", Toast.LENGTH_SHORT).show()
        }

        viewModel = ViewModelProvider(this).get(AddViewModel :: class.java)

        binding.switchType.setOnClickListener {
            if (binding.switchType.isChecked) {
                binding.textViewType.text = "Доход"
            } else {
                binding.textViewType.text = "Расход"
            }

        }

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
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


        binding.buttonAdd.setOnClickListener{
            Toast.makeText(activity, "Мы добавили!!!!", Toast.LENGTH_SHORT).show()
//            viewModel.liveData.setData(binding.editTextName.text.toString(),
//            binding.buttonCategory.text.toString(),
//            binding.buttonCategory.text.toString(),
//            binding.buttonDate.text.toString(),
//            binding.editTextCost.text.toString().toDouble())
        }

        return binding.root
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