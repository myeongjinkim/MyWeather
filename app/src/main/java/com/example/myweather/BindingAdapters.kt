package com.example.myweather

import android.content.res.Resources
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.data.remote.MyWeather
import com.example.myweather.data.remote.MyWeatherDaily
import com.example.myweather.data.remote.MyWeatherDailyTemp
import com.example.myweather.data.remote.MyWeatherHourly
import com.example.myweather.presentation.weather.WeatherDailyAdapter
import com.example.myweather.presentation.weather.WeatherHourlyAdapter
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.util.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("RecyclerHourlyData")
    fun bindingHourlyitem(recyclerView: RecyclerView, data: List<MyWeatherHourly>) {
        recyclerView.adapter = WeatherHourlyAdapter(data)
    }

    @JvmStatic
    @BindingAdapter("RecyclerDailyData")
    fun bindingDailyitem(recyclerView: RecyclerView, data: List<MyWeatherDaily>) {
        recyclerView.adapter = WeatherDailyAdapter(data)

    }

    @JvmStatic
    @BindingAdapter("iconData", "resource")
    fun bindingIconitem(imageView: ImageView, data: String, resource: String) {
        var icon = String.format(resource, data)
        Picasso.get().load(icon).error(R.drawable.ic_launcher_background).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("dateData", "arrayResource")
    fun bindingDateitem(textView: TextView, data: Int, arrayResource: Array<String>) {
        val cal = Calendar.getInstance()
        cal.time = Date(data * 1000L)
        val current = Calendar.getInstance()
        val currentDate = current.get(Calendar.DATE)
        val date = if(cal.get(Calendar.DATE) == currentDate) {
            arrayResource[0]
        }else if(cal.get(Calendar.DATE) == currentDate+1){
            arrayResource[1]
        }else{
            arrayResource[2]
        }
        textView.text = date
    }

    @JvmStatic
    @BindingAdapter("hourData", "resource", "arrayResource")
    fun bindingHouritem(textView: TextView, data: Int, resource: String, arrayResource: Array<String>) {
        val cal = Calendar.getInstance()
        cal.time = Date(data * 1000L)
        val ampm = if(cal.get(Calendar.AM_PM) == Calendar.AM) {
            arrayResource[0]
        }else{
            arrayResource[1]
        }
        textView.text = String.format(resource, ampm, cal.get(Calendar.HOUR).toString())
    }

    @JvmStatic
    @BindingAdapter("roundData", "resource")
    fun bindingRounditem(textView: TextView, data: Double, resource: String) {
        textView.text = String.format(resource, round(data))
    }

    @JvmStatic
    @BindingAdapter("percentData", "resource")
    fun bindingPercentRounditem(textView: TextView, data: Double, resource: String) {
        textView.text = String.format(resource, round(data*100))
    }

    @JvmStatic
    @BindingAdapter("dayData", "arrayResource")
    fun bindingDayitem(textView: TextView, data: Int, arrayResource: Array<String>) {
        val cal = Calendar.getInstance()
        cal.time = Date(data * 1000L)
        textView.text = arrayResource[cal.get(Calendar.DAY_OF_WEEK)-1]
    }

    @JvmStatic
    @BindingAdapter("roundTotTempData", "resource")
    fun bindingRoundTotTempitem(textView: TextView, data: MyWeather, resource: String) {
        textView.text = String.format(resource, round(data.daily[0].temp.max), round(data.daily[0].temp.min), round(data.current.feels_like))
    }

    @JvmStatic
    @BindingAdapter("roundDailyTempData", "resource")
    fun bindingRoundDailyTempitem(textView: TextView, data: MyWeatherDailyTemp, resource: String) {
        textView.text = String.format(resource, round(data.max),round(data.min))
    }

    fun round(data: Double):String{
        return Math.round(data).toString()
    }
}