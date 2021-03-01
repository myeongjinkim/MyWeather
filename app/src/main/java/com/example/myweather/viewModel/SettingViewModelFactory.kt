package com.example.myweather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.data.CityRepository

class SettingViewModelFactory(private val cityRepository: CityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CityRepository::class.java).newInstance(cityRepository)
    }
}