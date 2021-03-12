package com.example.myweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.data.remote.MyWeatherDaily
import com.example.myweather.data.remote.MyWeatherDailyTemp
import com.example.myweather.databinding.RecyclerviewDailyitemBinding
import java.util.*

class WeatherDailyAdapter(private val dataSet: List<MyWeatherDaily>): RecyclerView.Adapter<WeatherDailyAdapter.ViewHolder>() {
    class ViewHolder(private val binding: RecyclerviewDailyitemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViewHolder(item: MyWeatherDaily) {

            binding.weatherDaily = item
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val binding = RecyclerviewDailyitemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var item = dataSet.get(position);
        viewHolder.bindViewHolder(item);

    }

    override fun getItemCount() = dataSet.size

}