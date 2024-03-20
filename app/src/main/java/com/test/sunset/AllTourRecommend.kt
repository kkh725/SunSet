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
import com.test.sunset.databinding.ActivityRecommendTourRegionBinding
import com.test.sunset.itemss.NearByTourInfo
import com.test.sunset.itemss.TourData
import java.util.Locale.filter

class AllTourRecommend : AppCompatActivity() {

    private lateinit var binding: ActivityAllTourRecommendBinding
    private lateinit var AllTourInfoList : MutableList<NearByTourInfo>
    private lateinit var Emptylist : MutableList<NearByTourInfo>
    private lateinit var adapter: NearByTourAdapter
    private lateinit var filteredList: MutableList<NearByTourInfo>
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
        Emptylist= mutableListOf()

        val jsonread = assets.open("tourdata.json").reader().readText()
        Log.d("JSON STR", jsonread)

        //모든 여행지를 입력해넣음

        val tourdata = parseJsonToTourData(jsonread)
        if (tourdata!= null){
            for (record in tourdata.records){

                    val tourelement = NearByTourInfo(record.관광지명,record.소재지지번주소,record.관리기관전화번호,record.관광지소개)
                    AllTourInfoList.add(tourelement)
                Log.d("자료형",record::class.simpleName.toString())
                Log.d("자료형",tourelement::class.simpleName.toString())


                    if (record.관광지명.contains("일")){
                        val tourelement = NearByTourInfo(record.관광지명,record.소재지지번주소,record.관리기관전화번호,record.관광지소개)
                        Emptylist.add(tourelement)

                        Log.d("list",Emptylist.size.toString())
                    }
            }

        }

        for (i in AllTourInfoList){
            Log.d("자료형 i",i::class.simpleName.toString())

        }
        val rv_nearbytour = binding.recyclerView
        rv_nearbytour.layoutManager = LinearLayoutManager(this)
        adapter = NearByTourAdapter(AllTourInfoList,this)


        Log.d("str",AllTourInfoList.size.toString())

        //textinputedittext에 텍스트의 변환을 감지하는 리스너 오버라이딩
        binding.textInputEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                filteredList = mutableListOf()
                if (!s.isNullOrBlank()) { // s가 널이 아니고 빈 문자열이 아닌 경우에만 필터링 수행
                    filteredList.addAll(AllTourInfoList)
                    filteredList.clear()
                    val tourdata = parseJsonToTourData(jsonread)
                    if (tourdata!= null){
                        for (record in tourdata.records){
                            Log.d("자료형",record::class.simpleName.toString())
                            Log.d("자료형2",record.관광지명::class.simpleName.toString())
                            Log.d("자료형3",AllTourInfoList::class.simpleName.toString())
                            Log.d("자료형3",filteredList::class.simpleName.toString())

                            Log.d("자료형4",AllTourInfoList.size.toString())

                            //레코드로 순회해서 삽입하면 되는데 어째서 list로 순회하면 안되는지 확인
                            //아마 자료형의 문제일것같은데 어째서 안되는지?
                            //왜 Alltourinfolist[1]은 출력이안되는지?

                            if (record.관광지명.contains(s.toString())){
                                val tourelement = NearByTourInfo(record.관광지명,record.소재지지번주소,record.관리기관전화번호,record.관광지소개)
                                filteredList.add(tourelement)
                                Log.d("list",Emptylist.size.toString())
                            }
                        }
                        adapter.setFilteredList(filteredList)
                    }
                }
                else{
                    val tourdata = parseJsonToTourData(jsonread)
                    if (tourdata!= null){
                        for (record in tourdata.records){
                            val tourelement = NearByTourInfo(record.관광지명,record.소재지지번주소,record.관리기관전화번호,record.관광지소개)
                            filteredList.add(tourelement)
                        }
                    }

                }
                for (i in filteredList){
                    Log.d("dd 필터리스트의 관광지명", i.관광지명)

                }


                adapter.setFilteredList(filteredList)

                adapter.notifyDataSetChanged()
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
