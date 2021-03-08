package com.example.myweather.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.data.CityRepository
import com.example.myweather.data.local.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    application: Application
) : AndroidViewModel(application){

    fun requestSettingRepository(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
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
            cityRepository.getCityLocalDataSource().insert(
                City(
                    city_name = city,
                    lat = lat,
                    lon = lon
                )
            )
        }

    }

    fun onEditorAction(view: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            Log.d("asd", "${view!!.text}")
            view.clearFocus()
            val inputMethodManager = getApplication<Application>().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}