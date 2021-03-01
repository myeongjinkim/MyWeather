package com.example.myweather

import android.R
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.data.CityRepository
import com.example.myweather.data.WeatherRepository
import com.example.myweather.databinding.ActivitySettingBinding
import com.example.myweather.viewModel.SettingViewModel
import com.example.myweather.viewModel.SettingViewModelFactory
import com.example.myweather.viewModel.WeatherViewModel
import com.example.myweather.viewModel.WeatherViewModelFactory

class SettingActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        initSettingViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initSettingViewModel() {
        var settingViewModelFactory = SettingViewModelFactory(CityRepository(application))
        settingViewModel = ViewModelProvider(this, settingViewModelFactory).get(SettingViewModel::class.java)
    }

    fun myWeather(view: View) {
        settingViewModel.requestSettingRepository((view as Button).text as String)

    }
}