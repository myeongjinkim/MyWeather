package com.example.myweather

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myweather.BuildConfig.OpenWeatherKey
import com.example.myweather.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
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
    private var lat:Double = 0.0
    private var lon:Double = 0.0
    private lateinit var lang:String
    private lateinit var units:String
    private val myKey= OpenWeatherKey //openweather api key
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lang = "kr"
        units = "metric"
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        myApi =retrofit.create(MyApi::class.java)

    }
    fun myWeather(view: View){
        if((view as Button).text.equals("Seoul")){
            lat=37.56667;
            lon=126.97806;
        }else if((view).text.equals("Incheon")){
            lat=37.45639;
            lon=126.70528;
        }else if((view).text.equals("Busan")){
            lat=35.17944;
            lon=129.07556;
        }
        disposable = myApi.getWeather(lat, lon, myKey, lang, units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            var icon:String = "https://openweathermap.org/img/wn/${result.current.weather?.get(0)?.icon}@2x.png"
                            Picasso.get().load(icon).error(R.drawable.ic_launcher_background).into(binding.imageView);
                            binding.textView1.setText("도시 : ${view.text}")
                            binding.textView2.setText("날씨 : ${result.current.weather?.get(0)?.description}")
                            binding.textView3.setText("온도 : ${result.current?.temp}")
                            binding.textView4.setText("체감 온도 : ${result.current?.feels_like}")
                        },
                        { error -> Log.d("Error Msg:", error.message.toString()) }
                )

    }
    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}