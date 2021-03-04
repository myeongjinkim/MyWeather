package com.example.myweather.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.myweather.BuildConfig
import com.example.myweather.data.CityRepository
import com.example.myweather.data.WeatherRepository
import com.example.myweather.data.remote.MyWeatherCurrent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private var weatherRepository: WeatherRepository
) : ViewModel(){
    private var _MyWeatherCurrent = MutableLiveData<MyWeatherCurrent>()
    var MyWeatherCurrent = _MyWeatherCurrent

    private lateinit var city_Name:String
    private val myKey= BuildConfig.OpenWeatherKey //openweather api key
    private var lang = "kr"
    private var units = "metric"

    fun requestWeatherRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            var city = weatherRepository.getCityLocalDataSource().getAll()
            if(city!=null){
                city_Name = city[0].city_name
                weatherRepository.getWeatherRemoteDataSource(city[0].lat, city[0].lon, myKey, lang, units)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    Log.d("vm Msg:", "gdgd")
                                    _MyWeatherCurrent.postValue(result.current)
                                },
                                { error -> Log.d("Error Msg????:", error.message.toString()) }
                        )
            }
        }
    }
    fun getCity_name() = city_Name
}