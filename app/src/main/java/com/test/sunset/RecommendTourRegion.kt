package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.test.sunset.adapter2.NearByTourAdapter
import com.test.sunset.databinding.ActivityRecommendTourRegionBinding
import com.test.sunset.itemss.DestinationInfo
import com.test.sunset.itemss.NearByTourInfo
import com.test.sunset.itemss.TourData

/**
 * gps를 통한 현재위치와 그 위치기반 근처의 관광명소 보여주는 페이지
 */
class RecommendTourRegion : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendTourRegionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendTourRegionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tourInfoList : MutableList<NearByTourInfo> = mutableListOf()

        val jsonread = assets.open("tourdata.json").reader().readText()
        Log.d("JSON STR", jsonread)

        //gson 라이브러리를 사용해서 json문자열을 TourData객체로 변환하는 함수.
        val tourdata = parseJsonToTourData(jsonread)
        if (tourdata!= null){
            for (record in tourdata.records){

                if (record.소재지도로명주소.contains("강원도")){ // 실제로는 현재위치를 통해 추적할것
                    Log.d("관광지명", record.관광지명)
                    val tourelement = NearByTourInfo(record.관광지명,record.소재지지번주소,record.관리기관전화번호,record.관광지소개)
                    tourInfoList.add(tourelement)
                }
                // 필요한 정보들을 원하는 방식으로 처리합니다.
            }
        }
        for (i in tourInfoList){
            Log.d("dd",i.관광지명)
        }
        val rv_nearbytour = binding.recyclerView
        rv_nearbytour.layoutManager = LinearLayoutManager(this)
        rv_nearbytour.adapter = NearByTourAdapter(tourInfoList,this)
    }
    //gson 라이브러리를 사용해서 json문자열을 TourData객체로 변환하는 함수.
    private fun parseJsonToTourData(jsonString: String): TourData? {
        val gson = Gson()
        return gson.fromJson(jsonString, TourData::class.java)
    }
}