package com.example.myweather

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
import java.util.*

object BindingAdapters {

    val week = arrayOf("일", "월", "화", "수", "목", "금", "토")

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
    @BindingAdapter("iconData")
    fun bindingIconitem(imageView: ImageView, data: String) {
        var icon = "https://openweathermap.org/img/wn/${data}@2x.png"
        Picasso.get().load(icon).error(R.drawable.ic_launcher_background).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("roundTotTempData")
    fun bindingRoundTotTempitem(textView: TextView, data: MyWeather) {
        textView.text = "${Math.round(data.daily[0].temp.max)}℃/${Math.round(data.daily[0].temp.min)}℃ 체감 온도 ${Math.round(data.current.feels_like)}℃"
    }

    @JvmStatic
    @BindingAdapter("hourData")
    fun bindingHouritem(textView: TextView, data: Int) {
        val cal = Calendar.getInstance()
        cal.time = Date(data * 1000L)
        textView.text = "${cal.get(Calendar.HOUR)}시"
    }

    @JvmStatic
    @BindingAdapter("roundPopData")
    fun bindingRoundPopitem(textView: TextView, data: Double) {
        textView.text = "${Math.round(data)}%"
    }

    @JvmStatic
    @BindingAdapter("roundTempData")
    fun bindingRoundTempitem(textView: TextView, data: Double) {
        textView.text = "${Math.round(data)}℃"
    }

    @JvmStatic
    @BindingAdapter("dayData")
    fun bindingDayitem(textView: TextView, data: Int) {
        val cal = Calendar.getInstance()
        cal.time = Date(data * 1000L)
        textView.text = "${week[cal.get(Calendar.DAY_OF_WEEK)-1]}"
    }

    @JvmStatic
    @BindingAdapter("roundDailyTempData")
    fun bindingRoundDailyTempitem(textView: TextView, data: MyWeatherDailyTemp) {
        textView.text = "${Math.round(data.max)}℃/${Math.round(data.min)}℃"
    }

}