package com.example.expensemanager.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.database.Finance

class FinanceAdapter(
    private var financeItem: List<Finance>
): RecyclerView.Adapter<FinanceAdapter.FinanceViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.note, parent, false)

            return FinanceViewHolder(view)
        }

        override fun onBindViewHolder(holder: FinanceViewHolder, position: Int) {
            holder.itemView.apply {
               findViewById<TextView>(R.id.category_text_view).text = financeItem[position].category
               findViewById<TextView>(R.id.date_text_view).text = financeItem[position].date
               findViewById<TextView>(R.id.cost_text_view).text = financeItem[position].cost.toString()
            }
        }

        override fun getItemCount(): Int {
            return financeItem.size
        }

        fun setFinances(finances: List<Finance>) {
            this.financeItem = finances
        }

        inner class FinanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    }