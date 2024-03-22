package com.test.sunset.Repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.test.sunset.itemss.SunSetResponse
import com.test.sunset.retrofit.OpenWeatherMapService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {

    //이 클래스 자체에서 코루틴을 통해 비동기 작업을 수행하게 되면, sunset값이 항상 error가 될것이기 때문에
    // 뷰 모델에서 비동기 작업을 수행하게 해서 sunset값을 제대로 return 할 수 있게 만들자.
    suspend fun getSunSetAPI(lat: String, lng: String): String {
        var sunset= "error"
        var sunrise = "err"
        var noontime = "err"
        val retrofit = Retrofit.Builder().baseUrl("https://api.sunrise-sunset.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())).build()

        val weatherApiService: OpenWeatherMapService = retrofit.create(OpenWeatherMapService::class.java)
        Log.d("post2", "11")

        try {
            // API 호출
            val response: Response<SunSetResponse> = weatherApiService.getSunset(lat, lng)
            Log.d("post2", "22")

            if (response.isSuccessful) {
                val sunSetResponse: SunSetResponse? = response.body()
                if (sunSetResponse != null) {
                    sunset = amtopm(sunSetResponse.results.sunset)
                    sunrise = amtopm(sunSetResponse.results.sunrise)
                    noontime = amtopm(sunSetResponse.results.solarNoon)
                    Log.d("sunset/sunrise/noontime", amtopm(sunset))
                }
            } else {
                Log.d("post2", "44")
                // API 호출이 실패한 경우
                // response.errorBody() 등을 이용하여 실패 이유를 확인할 수 있음
                Log.d("post2", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.d("post2", "55")
            // 예외 처리
            Log.e("error", "Exception: ${e.message}", e)
        }
        return "$sunset $sunrise $noontime"
    }

    fun amtopm(timeString : String) :String{

        // 시간 문자열을 공백(" ")을 기준으로 나누어 시, 분, 초, 오전/오후를 추출합니다.
        val splitTime = timeString.split(" ")
        val hourMinuteSecond = splitTime[0].split(":")
        val hour = hourMinuteSecond[0].toInt()
        val minute = hourMinuteSecond[1].toInt()
        val second = hourMinuteSecond[2].toInt()
        var amPm = splitTime[1]

        // 9시간을 더합니다.
        var adjustedHour = hour + 9

        // 12시간 형식으로 변환합니다.
        if (adjustedHour >= 12) {
            adjustedHour -= 12
            // 오후인 경우 AM을 PM으로 변경합니다.
            if (amPm == "AM") {
                amPm = "PM"
            }
        }
        val adjustedTimeString = String.format("%d:%02d:%02d %s", adjustedHour, minute, second, amPm)
        return adjustedTimeString
    }
}