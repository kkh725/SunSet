package com.test.sunset;

import com.test.sunset.Item.WeatherResponse
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface OpenWeatherMapService {

    @FormUrlEncoded
    @POST("/getAreaRiseSetInfo") //?일지도
    suspend fun getSunset(@Field("location")location : String,
                          @Field("locdate")locdate : String,
                          @Field("ServiceKey")ServiceKey : String): Response<WeatherResponse>
}
