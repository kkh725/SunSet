package com.test.sunset

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.sunset.Repository.WeatherRepository
import com.test.sunset.itemss.DestinationInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {

    private lateinit var sunsetResult : String

    private val _sunset = MutableLiveData<String>()
    private val _sunrise = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _item = MutableLiveData<DestinationInfo>()

    val sunset: LiveData<String> = _sunset
    val sunrise: LiveData<String> = _sunrise
    val isLoading : LiveData<Boolean> = _isLoading
    val item : LiveData<DestinationInfo> = _item

    //view model 에서 repository에서의 api 호출을 진행하고, 코루틴으로 둘러싸서 제대로된 sunset값을 얻는다.
    fun fetchSunset(lat: String, lng: String) {
        _isLoading.postValue(true) // 로딩 시작

        CoroutineScope(Dispatchers.IO).launch {
            try {
                sunsetResult = WeatherRepository().getSunSetAPI(lat, lng)
                val parts = sunsetResult.split(" ") // 가져온 문자열을 sunset, sunrise 로 자른다.

                _sunset.postValue("일몰시각 : " + parts[0] + parts[1])
                _sunrise.postValue("일출시각 : " + parts[2] + parts[3])

            } catch (e: Exception) {
                // Handle error
                Log.e("MyViewModel", "Error fetching sunset: ${e.message}", e)
            }
            finally { //비동기 작업 수행 완료시
                _isLoading.postValue(false) //로딩 종료
            }
        }
    }

    fun updateItemList(newItem : DestinationInfo){
        _item.postValue(newItem)
    }
}