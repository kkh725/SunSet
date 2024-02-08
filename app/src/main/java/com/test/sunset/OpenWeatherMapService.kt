package com.test.sunset;

import com.test.sunset.Item.SunSetResponse
import com.test.sunset.Item.WeatherResponse
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET
import retrofit2.http.POST;
import retrofit2.http.Query

interface OpenWeatherMapService {

    //@FormUrlEncoded //이건 Post에서만 사용한다.
    @GET("json") //꼭 get인지 post인지 확인할것@@@@@@@@@@@@@@@@@
    suspend fun getSunset(@Query("lat")lat : String,
                          @Query("lng")lng : String): Response<SunSetResponse>
}
