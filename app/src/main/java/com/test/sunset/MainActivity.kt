package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.sunset.Adapter.DestinationAdapter
import com.test.sunset.itemss.DestinationInfo
import com.test.sunset.databinding.ActivityMainBinding

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

        viewModel.fetchSunset("36.720","-4.420")

        //옵저버 를 사용해 관찰가능
//        viewModel.sunset.observe(this, Observer {
//            binding.tv1.text = it
//        })

        val destinationlist : MutableList<DestinationInfo> = mutableListOf()

        destinationlist.add(DestinationInfo("유명 명소","1"))
        destinationlist.add(DestinationInfo("유명 명소","2"))
        destinationlist.add(DestinationInfo("유명 명소","3"))
        destinationlist.add(DestinationInfo("유명 명소","4"))
        destinationlist.add(DestinationInfo("유명 명소","5"))




        val rv_destination = binding.rvDestination
        rv_destination.adapter = DestinationAdapter(destinationlist)



    }


}