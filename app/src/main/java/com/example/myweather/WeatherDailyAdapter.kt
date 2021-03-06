package com.example.myweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.data.remote.MyWeatherDaily
import com.squareup.picasso.Picasso
import java.util.*

class WeatherDailyAdapter(private val dataSet: List<MyWeatherDaily>) : RecyclerView.Adapter<WeatherDailyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView1: TextView
        val imageView: ImageView
        val textView2: TextView
        val textView3: TextView
        val textView4: TextView

        init {
            textView1 = view.findViewById(R.id.textView1)
            imageView = view.findViewById(R.id.imageView)
            textView2 = view.findViewById(R.id.textView2)
            textView3 = view.findViewById(R.id.textView3)
            textView4 = view.findViewById(R.id.textView4)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recyclerview_dailyitem, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cal = Calendar.getInstance()
        cal.time = Date(dataSet[position].dt * 1000L)
        viewHolder.textView1.text = "${cal.get(Calendar.DATE)}일"
        var icon = "https://openweathermap.org/img/wn/${dataSet[position].weather[0].icon}@2x.png"
        Picasso.get().load(icon).error(R.drawable.ic_launcher_background).into(viewHolder.imageView);
        viewHolder.textView2.text = dataSet[position].weather[0].description
        viewHolder.textView3.text = "${dataSet[position].pop}%"
        viewHolder.textView4.text = "${dataSet[position].temp.max}℃/${dataSet[position].temp.min}℃"
    }

    override fun getItemCount() = dataSet.size

}