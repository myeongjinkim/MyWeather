package com.example.myweather

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.BuildConfig.OpenWeatherKey
import com.example.myweather.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var city:String

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWeatherViewModel()

    }

    private fun initWeatherViewModel() {
        weatherViewModelFactory = WeatherViewModelFactory(WeatherRepository())
        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory).get(WeatherViewModel::class.java)
        weatherViewModel.weatherRepositories.observe(this) {
            updateRepositories(it)
        }
    }

    private fun updateRepositories(repos: WeatherInfoRepositoryModel) {
        var icon = "https://openweathermap.org/img/wn/${repos.weather.get(0).icon}@2x.png"
        Picasso.get().load(icon).error(R.drawable.ic_launcher_background).into(binding.imageView);
        binding.textView1.setText("도시 : ${city}")
        binding.textView2.setText("날씨 : ${repos.weather.get(0)?.description}")
        binding.textView3.setText("온도 : ${repos.temp}")
        binding.textView4.setText("체감 온도 : ${repos.feels_like}")
    }

    fun myWeather(view: View) {
        if ((view as Button).text.equals("Seoul")) {
            weatherViewModel.lat = 37.56667
            weatherViewModel.lon = 126.97806
        } else if ((view).text.equals("Incheon")) {
            weatherViewModel.lat = 37.45639
            weatherViewModel.lon = 126.70528
        } else if ((view).text.equals("Busan")) {
            weatherViewModel.lat = 35.17944
            weatherViewModel.lon = 129.07556
        }
        city = view.text as String
        weatherViewModel.requestWeatherRepositories()

    }
}