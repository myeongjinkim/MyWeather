package com.example.myweather

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myweather.BuildConfig.OpenWeatherKey
import com.example.myweather.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myApi: MyApi
    private val myKey= OpenWeatherKey //openweather api key
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        myApi =retrofit.create(MyApi::class.java)

    }
    fun myWeather(view: View){
        disposable = myApi.getWeather((view as Button).text as String,myKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            binding.textView1.setText("도시 : ${(view).text}")
                            binding.textView2.setText("날씨 : ${result.weather?.get(0)?.main} - ${result.weather?.get(0)?.description}")
                            binding.textView3.setText("온도 : ${result.main?.temp}")
                            binding.textView4.setText("체감 온도 : ${result.main?.feels_like}")
                            binding.textView5.setText("최저 온도 : ${result.main?.temp_min}")
                            binding.textView6.setText("최고 온도 : ${result.main?.temp_max}")
                        },
                        { error -> Log.d("Error Msg:", error.message.toString()) }
                )

    }
    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}