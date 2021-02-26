package com.example.myweather

import android.app.Application
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
    var weatherRepositories = _weatherInfoRepositoryModel

    private lateinit var city_Name:String
    private val myKey= BuildConfig.OpenWeatherKey //openweather api key
    private var lang = "kr"
    private var units = "metric"

    fun requestWeatherRepositories() {
        CoroutineScope(Dispatchers.IO).launch {

            var city = weatherRepository.getCityDao().getAll()
            if(city!=null){
                city_Name = city[0].city_name
                weatherRepository.getRepositories(city[0].lat, city[0].lon, myKey, lang, units)
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
    fun getCity_name() = city_Name
}