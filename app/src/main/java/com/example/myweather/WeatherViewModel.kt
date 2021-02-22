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
    var weatherRepositories = _weatherInfoRepositoryModel

    fun requestWeatherRepositories(lat:Double, lon:Double, myKey:String, lang:String, units:String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Error Msg:", "${lat} ${lon} ${myKey} ${lang} ${units}")
            weatherRepository.getRepositories(lat, lon, myKey, lang, units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.d("Error Msg:", "gdgd")
                            _weatherInfoRepositoryModel.postValue(result.current)
                    },
                    { error -> Log.d("Error Msg:", error.message.toString()) }
                )
        }
    }
}