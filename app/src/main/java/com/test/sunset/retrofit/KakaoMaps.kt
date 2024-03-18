package com.test.sunset.retrofit

import com.test.sunset.itemss.KaKaoRegionResponseX
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoMaps {
    @GET("v2/local/geo/coord2regioncode.JSON") //꼭 get인지 post인지 확인할것@@@@@@@@@@@@@@@@@
    suspend fun getRegion_1depth(
        @Header("Authorization") key: String,
        @Query("y")y : String, //위도 latitude
        @Query("x")x : String): Response<KaKaoRegionResponseX> //경도 longitude
}