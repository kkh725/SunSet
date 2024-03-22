package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendTourRegionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        //들어올때 현재위치 받아서 들어옴
        val region = intent.getStringExtra("region").toString().split(" ")[0]
        Log.d("로그",region )

        val tourInfoList : MutableList<NearByTourInfo> = mutableListOf()

        val jsonread = assets.open("tourdata.json").reader().readText()
        Log.d("JSON STR", jsonread)

        val imageMap = mapOf(
            "일제 경성호국신사 계단(108계단)" to R.drawable.city_img,
            "찬바람재" to R.drawable.sunsetimg,
            "전쟁기념관" to R.drawable.ic_launcher_foreground,
            // 필요한 만큼 계속 추가
        )




        //gson 라이브러리를 사용해서 json문자열을 TourData객체로 변환하는 함수.
        val tourdata = parseJsonToTourData(jsonread)
        if (tourdata!= null){
            for (record in tourdata.records){

                if (record.소재지도로명주소.contains(region)){ // 실제로는 현재위치를 통해 추적할것
                    //관광지명이 있을경우 지정한이미지. 없을경우 기본이미지
                    val imgResId = imageMap[record.관광지명] ?: R.drawable.sunsetimg
                    Log.d("관광지명", record.관광지명)
                    val tourelement = NearByTourInfo(record.관광지명,record.소재지지번주소,record.관리기관전화번호,record.관광지소개,imgResId)
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
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // When the window gains focus, hide the system UI
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            // Set the content to appear under the system bars so that the content
                            // doesn't resize when the system bars hide and show.
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }
    }
}