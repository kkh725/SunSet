package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.sunset.Repository.WeatherRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherRepository().getWeatherApi("36.720","-4.420")
    }
}