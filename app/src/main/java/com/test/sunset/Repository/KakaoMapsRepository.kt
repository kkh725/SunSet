package com.test.sunset.Repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.GsonBuilder
import com.test.sunset.itemss.KaKaoRegionResponseX
import com.test.sunset.itemss.SunSetResponse
import com.test.sunset.retrofit.KakaoMaps
import com.test.sunset.retrofit.OpenWeatherMapService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KakaoMapsRepository {

    //이 클래스 자체에서 코루틴을 통해 비동기 작업을 수행하게 되면, sunset값이 항상 error가 될것이기 때문에
    // 뷰 모델에서 비동기 작업을 수행하게 해서 sunset값을 제대로 return 할 수 있게 만들자.
    suspend fun getRegionAPI(lat: String, lng: String): String {
        var region_1= "error"
        var region_2= "error"
        val retrofit = Retrofit.Builder().baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())).build()

        val kakaoMapsRepository: KakaoMaps = retrofit.create(KakaoMaps::class.java)
        Log.d("post2", "11")

        try {
            // API 호출
            val response: Response<KaKaoRegionResponseX> = kakaoMapsRepository.getRegion_1depth("KakaoAK 25eb3693ce906b268f75cbab3ecacec3",lat,lng)
            Log.d("post2", "22")

            if (response.isSuccessful) {
                val kakaoregionresponse: KaKaoRegionResponseX? = response.body()
                if (kakaoregionresponse != null) {
                    region_1 = kakaoregionresponse.documents[0].region1depthName
                    region_2 = kakaoregionresponse.documents[0].region2depthName
                    Log.d("poststst","$region_1 $region_2")
                }
            } else {
                Log.d("post2", "44")
                // API 호출이 실패한 경우
                // response.errorBody() 등을 이용하여 실패 이유를 확인할 수 있음
                Log.d("post23", response.errorBody()?.string()!!)
            }
        } catch (e: Exception) {
            Log.d("post2", "55")
            // 예외 처리
            Log.e("error", "Exception: ${e.message}", e)
        }
        return "$region_1 $region_2"
    }
}