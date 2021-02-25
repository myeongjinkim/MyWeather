package com.example.myweather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private var _weatherInfoRepositoryModel = MutableLiveData<WeatherInfoRepositoryModel>()
    var lat:Double = 0.0
    var lon:Double = 0.0
    private val myKey= BuildConfig.OpenWeatherKey //openweather api key
    private var lang = "kr"
    private var units = "metric"
    var weatherRepositories = _weatherInfoRepositoryModel

    fun requestWeatherRepositories(lat:Double,lon:Double) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherRepository.getRepositories(lat, lon, myKey, lang, units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.d("vm Msg:", "gdgd")
                            _weatherInfoRepositoryModel.postValue(result.current)
                    },
                    { error -> Log.d("Error Msg????:", error.message.toString()) }
                )
        }
    }
}