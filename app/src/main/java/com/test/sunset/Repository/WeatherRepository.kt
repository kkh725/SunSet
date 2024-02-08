package com.test.sunset.Repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.test.sunset.Item.SunSetResponse
import com.test.sunset.Item.WeatherResponse
import com.test.sunset.OpenWeatherMapService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository{


    fun getWeatherApi(lat :String , lng : String){
        val retrofit = Retrofit.Builder().baseUrl("https://api.sunrise-sunset.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())).build();
                //여기서 gson 으로 번역한게문제. json번역인데 xml을 쓸것이기 때문

        val WeatherApiService: OpenWeatherMapService = retrofit.create(OpenWeatherMapService::class.java)
        Log.d("post2","11")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // API 호출
                val response: Response<SunSetResponse> = WeatherApiService.getSunset(lat, lng)
                Log.d("post2","22")
                // UI 업데이트는 Main 스레드에서 수행
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) { //응답 성공시
                        val post: SunSetResponse? = response.body()
                        if (post != null) {
                            // API 호출이 성공하고, 응답 데이터를 post 변수에 얻어옴
                            // post 변수를 이용하여 필요한 처리 수행
                            Log.d("post2","33")
//                            Log.d("post", post.title.toString())
                            Log.d("post2",response.body().toString())


                        }
                        else {
                            Log.d("post2","44")
                            // API 호출이 실패한 경우
                            // response.errorBody() 등을 이용하여 실패 이유를 확인할 수 있음
                            Log.d("post2",response.errorBody().toString())
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("post2","55")
                // 예외 처리
                Log.e("error", "Exception: ${e.message}", e)
            }
        }
    }

}