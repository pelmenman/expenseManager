package com.example.expensemanager.view
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.expensemanager.R

class ListFragment : Fragment(R.layout.fragment_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.beb).setOnClickListener {
            view.findViewById<TextView>(R.id.fragmentList).text = "U've clicked!!"
        }
    }
}