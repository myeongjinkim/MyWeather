package com.example.myweather.viewModel

import android.app.Application
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myweather.data.CityRepository
import com.example.myweather.data.local.City
import com.example.myweather.data.remote.MyWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
        private val cityRepository: CityRepository,
        application: Application
) : AndroidViewModel(application){

    private val context = getApplication<Application>().applicationContext
    val inputMethodManager = getApplication<Application>().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    var cityAddress = ObservableField<String>()
    var editCityAddress = ObservableField<String>()

    private var _BackPressed = MutableLiveData<Boolean>()
    var BackPressed = _BackPressed

    lateinit var myCity:City

    fun requestSettingRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            cityRepository.getCityLocalDataSource().deleteAll()
            cityRepository.getCityLocalDataSource().insert(myCity)
        }

    }

    fun onEditorAction(view: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            var text = view!!.text.toString()
            closeKeyboard(view)
            var geocoder = Geocoder(context)

            try {
                var address = geocoder.getFromLocationName(text, 100).get(0)
                cityAddress.set(address.getAddressLine(0))
                myCity = City(address.getAddressLine(0),address.latitude,address.longitude)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return true
        }
        return false
    }

    fun closeKeyboard(view: View) {
        view.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun textClear(view: View){
        editCityAddress.set("")
    }

    fun setCity(view: View){
        requestSettingRepository()
        _BackPressed.postValue(true)
    }


}