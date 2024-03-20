package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.test.sunset.adapter2.NearByTourAdapter
import com.test.sunset.databinding.ActivityAllTourRecommendBinding
import com.test.sunset.itemss.NearByTourInfo
import com.test.sunset.itemss.TourData

class AllTourRecommend : AppCompatActivity() {

    private lateinit var binding: ActivityAllTourRecommendBinding
    private lateinit var AllTourInfoList : MutableList<NearByTourInfo>
    private lateinit var adapter: NearByTourAdapter
    private lateinit var filteredList: MutableList<NearByTourInfo>
    private lateinit var jsonread : String
    private lateinit var tourdata : TourData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllTourRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //텍스트에 유니코드 넣기 이모지
        //https://apps.timwhitlock.info/emoji/tables/unicode 이모지 사이트

        val fireWorkUnicode = 0x1F687
        val unicode = 0x1F335
        binding.textView4.text = "${String(Character.toChars(fireWorkUnicode))} 전국 관광명소"
        binding.textInputLayout.hint = "${String(Character.toChars(unicode))} 관광지명 검색"

        AllTourInfoList= mutableListOf()

        jsonread = assets.open("tourdata.json").reader().readText()
        Log.d("JSON STR", jsonread)

        //모든 여행지를 입력해넣음

        tourdata = parseJsonToTourData(jsonread)!!
        for (record in tourdata.records){

                val tourelement = NearByTourInfo(record.관광지명,record.소재지지번주소,record.관리기관전화번호,record.관광지소개)
                AllTourInfoList.add(tourelement)
        }


        val rv_nearbytour = binding.recyclerView
        rv_nearbytour.layoutManager = LinearLayoutManager(this)
        adapter = NearByTourAdapter(AllTourInfoList,this)

        //textinputedittext에 텍스트의 변환을 감지하는 리스너 오버라이딩
        binding.textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                //텍스트가 입력되었을때 광광지명과 비교하고, 관광지명에 포함되어있는경우 리사이클러뷰 업데이트

                filteredList = mutableListOf()
                if (!s.isNullOrBlank()) { // s가 널이 아니고 빈 문자열이 아닌 경우에만 필터링 수행
                    filteredList.addAll(AllTourInfoList)
                    filteredList.clear()
                    for (record in tourdata.records) {

                        if (record.관광지명.contains(s.toString())) {
                            val tourelement = NearByTourInfo(
                                record.관광지명,
                                record.소재지지번주소,
                                record.관리기관전화번호,
                                record.관광지소개
                            )
                            filteredList.add(tourelement)
                        }
                    }
                    adapter.setFilteredList(filteredList)
                }
                else{
                        for (record in tourdata.records){
                            val tourelement = NearByTourInfo(record.관광지명,record.소재지지번주소,record.관리기관전화번호,record.관광지소개)
                            filteredList.add(tourelement)
                        }
                    }

                adapter.setFilteredList(filteredList)
            }

        })
        rv_nearbytour.adapter = adapter
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
