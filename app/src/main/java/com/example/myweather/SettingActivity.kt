package com.example.myweather

import android.R
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myweather.databinding.ActivitySettingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var db: CityDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        db = Room.databaseBuilder(
            applicationContext,
            CityDatabase::class.java, "city"
        ).build()
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

    fun myWeather(view: View) {
        var lat:Double = 0.0
        var lon:Double = 0.0
        if ((view as Button).text.equals("Seoul")) {
            lat = 37.56667
            lon = 126.97806
        } else if ((view).text.equals("Incheon")) {
            lat = 37.45639
            lon = 126.70528
        } else if ((view).text.equals("Busan")) {
            lat = 35.17944
            lon = 129.07556
        }
        var city = view.text as String
        CoroutineScope(Dispatchers.IO).launch {
            if(db.cityDao().load(city)==null){
                db.cityDao().insert(
                    City(
                        city_name = city,
                        lat = lat,
                        lon = lon
                    )
                )
            }
        }
    }
}