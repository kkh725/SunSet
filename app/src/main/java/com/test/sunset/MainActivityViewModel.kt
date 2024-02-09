package com.test.sunset

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.sunset.Repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {

    private lateinit var sunsetResult : String
    private val _sunset = MutableLiveData<String>()
    val sunset: LiveData<String> = _sunset

    //view model 에서 repository에서의 api 호출을 진행하고, 코루틴으로 둘러싸서 제대로된 sunset값을 얻는다.
    fun fetchSunset(lat: String, lng: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                sunsetResult = WeatherRepository().getSunSetAPI(lat, lng)
                _sunset.postValue(sunsetResult)

            } catch (e: Exception) {
                // Handle error
                Log.e("MyViewModel", "Error fetching sunset: ${e.message}", e)
            }
        }
    }
}