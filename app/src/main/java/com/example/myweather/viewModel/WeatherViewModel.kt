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
}