package com.example.myweather

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweather.data.remote.MyWeather
import com.example.myweather.data.remote.MyWeatherDaily
import com.example.myweather.data.remote.MyWeatherHourly
import com.example.myweather.databinding.ActivityMainBinding
import com.example.myweather.viewModel.WeatherViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var pressedTime: Long = 0
    private val finishIntervalTime: Long = 2000
    private lateinit var binding: ActivityMainBinding

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar);

        initWeatherViewModel()
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        weatherViewModel.requestWeatherRepositories()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_button -> { // 오른쪽 상단 버튼 눌렀을 때
                val intent = Intent(applicationContext, SettingActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > finishIntervalTime + pressedTime) {
            pressedTime = System.currentTimeMillis()
            Toast.makeText(this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }else{
            finish()
        }
    }

    private fun initWeatherViewModel() {
        weatherViewModel.MyWeather.observe(this) {
            updateView(it)
        }
    }

    private fun initRecyclerView(){
        binding.hourlyRecyclerView.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
        binding.dailyRecyclerView.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
    }

    private fun updateView(repos: MyWeather) {

        binding.textView1.setText("${weatherViewModel.getCity_name()}")
        var icon = "https://openweathermap.org/img/wn/${repos.current.weather[0].icon}@2x.png"
        Picasso.get().load(icon).error(R.drawable.ic_launcher_background).into(binding.imageView);
        binding.textView2.setText("${Math.round(repos.current.temp)}℃")
        binding.textView3.setText("${repos.current.weather[0].description}")
        binding.textView4.setText("${Math.round(repos.daily[0].temp.max)}℃/${Math.round(repos.daily[0].temp.min)}℃ 체감 온도 ${Math.round(repos.current.feels_like)}℃")

        binding.hourlyRecyclerView.adapter = WeatherHourlyAdapter(repos.hourly)
        binding.dailyRecyclerView.adapter = WeatherDailyAdapter(repos.daily)
    }

}