package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.test.sunset.itemss.NearByTourInfo
import com.test.sunset.itemss.TourData

class RecommendTourRegion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_tour_region)

        val tourInfoList = mutableListOf<NearByTourInfo>()

        val jsonread = assets.open("tourdata.json").reader().readText()
        Log.d("JSON STR", jsonread)

        //gson 라이브러리를 사용해서 json문자열을 TourData객체로 변환하는 함수.
        val tourdata = parseJsonToTourData(jsonread)
        if (tourdata!= null){
            for (record in tourdata.records){

                if (record.소재지도로명주소.contains("강원도")){ // 실제로는 현재위치를 통해 추적할것
                    Log.d("관광지명", record.관광지명)
                    val tourelement = NearByTourInfo(record.관광지명)
                    tourInfoList.add(tourelement)
                    Log.d("관광지명", tourInfoList[1].관광지명)
                }
                // 필요한 정보들을 원하는 방식으로 처리합니다.
            }
        }
    }
    //gson 라이브러리를 사용해서 json문자열을 TourData객체로 변환하는 함수.
    private fun parseJsonToTourData(jsonString: String): TourData? {
        val gson = Gson()
        return gson.fromJson(jsonString, TourData::class.java)
    }
}