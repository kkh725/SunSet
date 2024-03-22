package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.test.sunset.adapter2.DestinationAdapter
import com.test.sunset.itemss.DestinationInfo
import com.test.sunset.databinding.ActivityMainBinding
import com.test.sunset.gps.RequestPermissionsUtil
import com.test.sunset.graph.SunsetGraphManager

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    lateinit var region : String

    override fun onStart() {
        RequestPermissionsUtil(this, viewModel).requestLocation()
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.myViewModel = viewModel
        setContentView(binding.root)

        viewModel.loadingStart()
        RequestPermissionsUtil(this, viewModel).requestLocation()

        //위치 허용 확인 시 위치 추적
        if (RequestPermissionsUtil(this, viewModel).isLocationPermitted()) {
            RequestPermissionsUtil(this, viewModel).requestLocationUpdates()
        }
        else{
            RequestPermissionsUtil(this, viewModel).requestLocationUpdates()
            Log.d("MainActivity", "위치 권한 거부")
        }


        //데이터 바인딩을 사용하기 때문에 옵저버 사용x
        //mvvm에서는 그러면 주로 databinding, live data, viewmodel, repository, data.등이 어우러져 사용된다.

        viewModel.changeImage(binding.root.context,binding.imageView3)
        binding.lifecycleOwner = this

        //좌표가 바뀔때마다 리스너가 호출되어 뷰모델의 위경도를 바꾼다.
        //위경도가 변경될때마다 옵저버로 감지하여 fetchsunset메서드 실행.
        viewModel.lon.observe(this, Observer {
            viewModel.fetchSunset(viewModel.lat.value.toString(), viewModel.lon.value.toString())
        })
        //x좌표, Y좌표를 입력하면 view model 에서 일몰,일출값 수정
        //좌표값이 바뀔때마다 noontime라이브데이터값이 수정되고 그때마다 그래프 새로그린다.
        viewModel.noontime.observe(this, Observer {  //it-> noontime의 바뀌는값
            val sunset = viewModel.splitTime(viewModel.sunset.value.toString())
            val sunrise = viewModel.splitTime(viewModel.sunrise.value.toString())
            val noontime = viewModel.splitTime(viewModel.noontime.value.toString())
            SunsetGraphManager(binding, sunset, sunrise, noontime).makeSunsetGraph()
        })

        viewModel.region.observe(this, Observer {
            region = it
        })

        val rv_destination = binding.rvDestination

        val destinationlist: MutableList<DestinationInfo> = mutableListOf()

        destinationlist.add(DestinationInfo("내 주변 관광명소","2", ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("전국 관광명소","1",ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("-","1", ContextCompat.getColor(binding.root.context, R.color.custom_red)))
        destinationlist.add(DestinationInfo("-","2", ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("-","1",ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("-","2", ContextCompat.getColor(binding.root.context, R.color.custom_red)))

        rv_destination.adapter = DestinationAdapter(destinationlist,viewModel)


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