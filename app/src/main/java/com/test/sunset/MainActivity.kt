package com.test.sunset

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.sunset.Adapter.DestinationAdapter
import com.test.sunset.itemss.DestinationInfo
import com.test.sunset.databinding.ActivityMainBinding
import com.test.sunset.graph.CustomMarkerView
import com.test.sunset.graph.SunsetGraphManager

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        //데이터 바인딩을 사용하기 때문에 옵저버 사용x
        //mvvm에서는 그러면 주로 databinding, live data, viewmodel, repository, data.등이 어우러져 사용된다.

        binding.myViewModel = viewModel
        binding.lifecycleOwner = this

        //x좌표, Y좌표를 입력하면 view model 에서 일몰,일출값 수정
        viewModel.fetchSunset("36.720","-4.420")
        viewModel.sunset.observe(this, Observer { sunsetValue ->
            sunsetValue?.let {
                Log.d("dddd", viewModel.splitTime(sunsetValue.toString()).toString())
            } ?: Log.d("dddd", viewModel.splitTime(viewModel.sunset.value.toString()).toString())
        })



        val rv_destination = binding.rvDestination

        val destinationlist : MutableList<DestinationInfo> = mutableListOf()

        destinationlist.add(DestinationInfo("유명 명소","1", ContextCompat.getColor(binding.root.context, R.color.custom_red)))
        destinationlist.add(DestinationInfo("유명 명소","2",ContextCompat.getColor(binding.root.context, R.color.black)))
        destinationlist.add(DestinationInfo("유명 명소","3",ContextCompat.getColor(binding.root.context, R.color.custom_yello)))
        destinationlist.add(DestinationInfo("유명 명소","4", ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("유명 명소","5",ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("유명 명소","1", ContextCompat.getColor(binding.root.context, R.color.custom_red)))
        destinationlist.add(DestinationInfo("유명 명소","2",ContextCompat.getColor(binding.root.context, R.color.black)))
        destinationlist.add(DestinationInfo("유명 명소","3",ContextCompat.getColor(binding.root.context, R.color.custom_yello)))
        destinationlist.add(DestinationInfo("유명 명소","4", ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("유명 명소","5",ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("유명 명소","2",ContextCompat.getColor(binding.root.context, R.color.black)))
        destinationlist.add(DestinationInfo("유명 명소","3",ContextCompat.getColor(binding.root.context, R.color.custom_yello)))
        destinationlist.add(DestinationInfo("유명 명소","4", ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))
        destinationlist.add(DestinationInfo("유명 명소","5",ContextCompat.getColor(binding.root.context, R.color.custom_whiteblur)))

        rv_destination.adapter = DestinationAdapter(destinationlist)
        SunsetGraphManager(binding,1,2,3).makeSunsetGraph()





    }

    }




