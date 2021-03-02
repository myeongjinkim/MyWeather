package com.example.myweather.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myweather.data.CityRepository
import com.example.myweather.data.local.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {

    fun requestSettingRepository(city:String) {
        CoroutineScope(Dispatchers.IO).launch {
            var lat:Double = 0.0
            var lon:Double = 0.0
            if (city.equals("Seoul")) {
                lat = 37.56667
                lon = 126.97806
            } else if (city.equals("Incheon")) {
                lat = 37.45639
                lon = 126.70528
            } else if (city.equals("Busan")) {
                lat = 35.17944
                lon = 129.07556
            }
            cityRepository.getCityLocalDataSource().deleteAll()
            cityRepository.getCityLocalDataSource().insert(City(
                    city_name = city,
                    lat = lat,
                    lon = lon
            ))
        }
    }

}