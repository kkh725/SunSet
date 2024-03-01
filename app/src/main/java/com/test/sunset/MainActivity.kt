package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
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


        //목적지 리스트에 목적지정보와 종류, 색이 들어간 목록을 추가한다.
        //어댑터에 추가하면 adatper실행, -> 뷰홀더 실행 -> 데이터바인딩으로 엮여있는 bind가 수행되기때문에 xml에서 잘 결합됨.

        //뷰모델에서 아이템리스트의 추가정보가 들어오는지 관찰하여, 바뀔때마다 recyclerview 를 업데이트한다.
        viewModel.item.observe(this, Observer {
            destinationlist.add(it)
        })

        val rv_destination = binding.rvDestination
        rv_destination.adapter = DestinationAdapter(destinationlist)
        //vm의 업데이트 아이템리스트 메서드가 실행되면 매개변수인 destinationinfo가 livedata값으로 변환되면서 관찰되게 된다.
        var example1 = DestinationInfo("유명 명소","1", ContextCompat.getColor(binding.root.context, R.color.custom_red))
        viewModel.updateItemList(example1)

        //viewmdel 에서 recycler view 의 데이터가 추가되는걸
        destinationlist.add(DestinationInfo("유명 명소","1", ContextCompat.getColor(binding.root.context, R.color.custom_red)))
        destinationlist.add(DestinationInfo("유명 명소","2",ContextCompat.getColor(binding.root.context, R.color.black)))
        destinationlist.add(DestinationInfo("유명 명소","3",ContextCompat.getColor(binding.root.context, R.color.black)))
        destinationlist.add(DestinationInfo("유명 명소","4", ContextCompat.getColor(binding.root.context, R.color.black)))
        destinationlist.add(DestinationInfo("유명 명소","5",ContextCompat.getColor(binding.root.context, R.color.custom_yello)))






    }


}