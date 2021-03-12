package com.example.myweather.viewModel

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.BuildConfig
import com.example.myweather.R
import com.example.myweather.WeatherDailyAdapter
import com.example.myweather.WeatherHourlyAdapter
import com.example.myweather.data.WeatherRepository
import com.example.myweather.data.remote.*
import com.example.myweather.databinding.RecyclerviewDailyitemBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.annotation.Nullable
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private var weatherRepository: WeatherRepository
) : ViewModel(){
    private var _MyWeather = MutableLiveData<MyWeather>()
    var MyWeather = _MyWeather

    private val myKey= BuildConfig.OpenWeatherKey //openweather api key
    private var lang = "kr"
    private var units = "metric"
    private var exclude = "minutely,alerts"

    var cityName = ObservableField<String>()

    fun requestWeatherRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            var city = weatherRepository.getCityLocalDataSource().getAll()
            cityName.set(city[0].city_name)

            weatherRepository.getWeatherRemoteDataSource(city[0].lat, city[0].lon, myKey, lang, units, exclude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result ->
                            _MyWeather.postValue(result)
                        },
                        { error -> Log.d("Error Msg:", error.message.toString()) }
                    )
        }
    }

    companion object {
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
            Picasso.get().load(icon).error(R.drawable.ic_launcher_background).into(imageView);
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

}