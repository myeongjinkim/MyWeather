package com.example.myweather.viewModel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.myweather.BuildConfig
import com.example.myweather.R
import com.example.myweather.data.WeatherRepository
import com.example.myweather.data.remote.MyWeather
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    var icon = ObservableField<String>()
    var currentTemp = ObservableField<String>()
    var description = ObservableField<String>()
    var tempInfo = ObservableField<String>()

    fun requestWeatherRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            var city = weatherRepository.getCityLocalDataSource().getAll()
            weatherRepository.getWeatherRemoteDataSource(city[0].lat, city[0].lon, myKey, lang, units, exclude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                _MyWeather.postValue(result)
                                cityName.set(city[0].city_name)
                                icon.set("https://openweathermap.org/img/wn/${result.current.weather[0].icon}@2x.png")
                                currentTemp.set("${Math.round(result.current.temp)}℃")
                                description.set("${result.current.weather[0].description}")
                                tempInfo.set("${Math.round(result.daily[0].temp.max)}℃/${Math.round(result.daily[0].temp.min)}℃ 체감 온도 ${Math.round(result.current.feels_like)}℃")
                            },
                            { error -> Log.d("Error Msg:", error.message.toString()) }
                    )
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, imageUrl: String?) {
            Picasso.get().load(imageUrl).error(R.drawable.ic_launcher_background).into(imageView);
        }
    }

}