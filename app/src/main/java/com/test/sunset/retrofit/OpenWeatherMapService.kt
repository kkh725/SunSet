package com.test.sunset.retrofit;

import com.test.sunset.itemss.SunSetResponse
import retrofit2.Response;
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface OpenWeatherMapService {

    //@FormUrlEncoded //이건 Post에서만 사용한다.
    @GET("json") //꼭 get인지 post인지 확인할것@@@@@@@@@@@@@@@@@
    suspend fun getSunset(
                            @Query("lat")lat : String,
                          @Query("lng")lng : String): Response<SunSetResponse>
}
