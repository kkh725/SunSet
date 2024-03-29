package com.test.sunset

import android.content.Context
import android.location.Location
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.test.sunset.Repository.KakaoMapsRepository
import com.test.sunset.Repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale

class MainActivityViewModel() : ViewModel() {

    var sunsetResult2 = ""

    private val _sunset = MutableLiveData<String>()
    private val _sunrise = MutableLiveData<String>()
    private val _noontime = MutableLiveData<String>()
    private val _lat = MutableLiveData<String>()
    private val _lon = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _region = MutableLiveData<String>()

    val sunset: LiveData<String> = _sunset
    val sunrise: LiveData<String> = _sunrise
    val noontime : LiveData<String> = _noontime
    val lat : LiveData<String> = _lat
    val lon : LiveData<String> = _lon
    val isLoading : LiveData<Boolean> = _isLoading
    val region : LiveData<String> = _region

    fun loadingStart (){ //로딩시작
        _isLoading.postValue(true)
    }
    fun getLatLon(location : Location){
        _lat.value = (location.latitude.toString())
        _lon.value = (location.longitude.toString())
    }

    //view model 에서 repository에서의 api 호출을 진행하고, 코루틴으로 둘러싸서 제대로된 sunset값을 얻는다.
    fun fetchSunset(lat: String, lng: String) {
        viewModelScope.launch {
            try {
                val sunsetResult = withContext(Dispatchers.IO) {
                    WeatherRepository().getSunSetAPI(lat, lng)

                }
                val Kakaoresult = withContext(Dispatchers.Default) {
                    KakaoMapsRepository().getRegionAPI(lat, lng)
                }

                val parts = sunsetResult.split(" ")
                Log.e("MyViewModel", sunsetResult + parts[4] + parts[5])
                Log.e("MyViewModel", Kakaoresult + lat + lng)

                Log.e("MyViewModel222", sunsetResult2)
                _sunset.postValue(parts[0] + parts[1])
                _sunrise.postValue(parts[2] + parts[3])
                _noontime.postValue(parts[4] + parts[5])
                _region.postValue(Kakaoresult)
            } catch (e: Exception) {
                // Handle error
                Log.e("MyViewModel", "Error fetching sunset: ${e.message}", e)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }


    fun splitTime(time : String) : Pair<Int,Int>{
        val hour = time.split(":")[0].toInt()
        val min = time.split(":")[1].toInt()

        return Pair(hour,min)
    }

    fun changeImage(context: Context, imageView: ImageView) {
        val currentTime = Calendar.getInstance().time
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formattedTime = sdf.format(currentTime)

        val sunriseTime = "06:30" // 예시: 일출 시간
        val sunsetTime = "18:30" // 예시: 일몰 시간

        if (isTimeInRange(formattedTime,sunriseTime,sunsetTime)){
            Glide.with(context).
            load("https://images.unsplash.com/photo-1484766280341-87861644c80d?" +
                    "q=80&w=2832&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D").
            fitCenter().
            into(imageView)
        }
        else{
            Glide.with(context).
            load("https://images.unsplash.com/photo-1488866022504-f2584929ca5f?q=80&w=2924&auto=format&" +
                    "fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D").
            fitCenter().
            into(imageView)
        }
    }

    private fun isTimeInRange(time: String, startTime: String, endTime: String): Boolean {
        return time in startTime..endTime
    }



}