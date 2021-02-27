package com.example.testfalabella.ui.home

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.indicators.Indicators
import com.example.testfalabella.R
import com.example.testfalabella.ui.common.inflate
import kotlinx.android.synthetic.main.item_indicators_view.view.*

class HomeAdapter (private val listener: (Indicators) -> Unit) : RecyclerView.Adapter<HomeAdapter.ViewHolder>()  {

    private var listOfIndicators = arrayListOf<Indicators>()

    fun updateListIndicators(indicatorDetail: ArrayList<Indicators>) {
        this.listOfIndicators.clear()
        this.listOfIndicators.addAll(indicatorDetail)
        notifyDataSetChanged()
        Log.e("LIST","COUNT "+this.listOfIndicators.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_indicators_view, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listOfIndicators.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val indicatorDetail = listOfIndicators[position]
        holder.bind(indicatorDetail)
        holder.itemView.setOnClickListener { listener(indicatorDetail) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(indicator: Indicators) {

            itemView.tvListName.text = indicator.nombre
            itemView.tvListValue.text = indicator.valor

        }
    }

}