package com.example.myweather

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myweather.BuildConfig.OpenWeatherKey
import com.example.myweather.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myApi: MyApi
    private val myKey= OpenWeatherKey //openweather api key
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        myApi =retrofit.create(MyApi::class.java)

    }
    fun myWeather(view: View){
        lateinit var getCall: Call<MyWeather>
        if((view as Button).text.equals("서울")){
            getCall = myApi.getWeather("Seoul",myKey)
        }else if((view).text.equals("인천")){
            getCall = myApi.getWeather("Incheon",myKey)
        }else if((view).text.equals("부산")){
            getCall = myApi.getWeather("Busan",myKey)
        }


        getCall.enqueue(object : Callback<MyWeather> {

            override fun onResponse(call: Call<MyWeather>, response: Response<MyWeather>) {
                if (response.isSuccessful()) {
                    var item = response.body()

                    binding.textView1.setText("도시 : ${(view).text}")
                    binding.textView2.setText("날씨 : ${item?.weather?.get(0)?.main} - ${item?.weather?.get(0)?.description}")
                    binding.textView3.setText("온도 : ${item?.main?.temp}")
                    binding.textView4.setText("체감 온도 : ${item?.main?.feels_like}")
                    binding.textView5.setText("최저 온도 : ${item?.main?.temp_min}")
                    binding.textView6.setText("최고 온도 : ${item?.main?.temp_max}")

                } else {
                    Log.d("aa", "Status Code : " + response.code())
                }
            }

            override fun onFailure(call: Call<MyWeather>, t: Throwable) {
                Log.d("aa", "Fail msg : " + t.message)
            }
        })

    }
}