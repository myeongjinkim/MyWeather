package com.example.myweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.data.remote.MyWeatherDaily
import com.example.myweather.data.remote.MyWeatherHourly
import com.example.myweather.databinding.RecyclerviewDailyitemBinding
import com.squareup.picasso.Picasso
import java.util.*

class WeatherDailyAdapter(private val dataSet: List<MyWeatherDaily>): RecyclerView.Adapter<WeatherDailyAdapter.ViewHolder>() {
    class ViewHolder(private val binding: RecyclerviewDailyitemBinding) : RecyclerView.ViewHolder(binding.root) {
        val week = arrayOf("일", "월", "화", "수", "목", "금", "토")
        fun bindViewHolder(item: MyWeatherDaily) {
            val cal = Calendar.getInstance()
            cal.time = Date(item.dt * 1000L)
            binding.textView1.text = "${week[cal.get(Calendar.DAY_OF_WEEK)-1]}"
            var icon = "https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"
            Picasso.get().load(icon).error(R.drawable.ic_launcher_background).into(binding.imageView);
            binding.textView2.text = item.weather[0].description
            binding.textView3.text = "${Math.round(item.pop)}%"
            binding.textView4.text = "${Math.round(item.temp.max)}℃/${Math.round(item.temp.min)}℃"
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