package com.example.myweather.presentation.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.data.remote.MyWeatherHourly
import com.example.myweather.databinding.RecyclerviewHourlyitemBinding

class WeatherHourlyAdapter(private val dataSet: List<MyWeatherHourly>) : RecyclerView.Adapter<WeatherHourlyAdapter.ViewHolder>() {
    class ViewHolder(private val binding: RecyclerviewHourlyitemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViewHolder(item: MyWeatherHourly) {
            binding.weatherHourly = item
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewHourlyitemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var item = dataSet.get(position * 3);
        viewHolder.bindViewHolder(item);

    }

    override fun getItemCount() = dataSet.size/3

}
