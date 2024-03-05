package com.test.sunset

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
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

//        //스크롤뷰 스와이프 시 이미지 크기변경
        val rv_destination = binding.rvDestination

        rv_destination.adapter = DestinationAdapter(destinationlist)
        //vm의 업데이트 아이템리스트 메서드가 실행되면 매개변수인 destinationinfo가 livedata값으로 변환되면서 관찰되게 된다.
        var example1 = DestinationInfo("유명 명소","1", ContextCompat.getColor(binding.root.context, R.color.custom_red))
        viewModel.updateItemList(example1)

        //viewmdel 에서 recycler view 의 데이터가 추가되는걸
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

        makeSunsetGraph(0,4)





    }

    //sunset 차트 그리기
    private fun makeSunsetGraph(sunset : Int, sunrise : Int){
        val sunriseTime = 6 * 60 * 60 + 10 * 60 // 일출 시각을 초로 변환
        val sunsetTime = 18 * 60 * 60 + 32 * 60 + 5 // 일몰 시각을 초로 변환

        val entries = ArrayList<com.github.mikephil.charting.data.Entry>()
        entries.add(com.github.mikephil.charting.data.Entry(sunrise.toFloat(), sunset.toFloat()))
        entries.add(com.github.mikephil.charting.data.Entry(1f, 1f))
        entries.add(com.github.mikephil.charting.data.Entry(3f, 5f))
        entries.add(com.github.mikephil.charting.data.Entry(10f, 5f))
        entries.add(com.github.mikephil.charting.data.Entry(15f, 0f))

        val lineDataSet = LineDataSet(entries,"sunset chart")
        lineDataSet.lineWidth = 2f
        lineDataSet.circleRadius = 6f
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"))
        lineDataSet.circleHoleColor = R.color.custom_yello
        lineDataSet.color = Color.parseColor("#FFA1B4DC")
        lineDataSet.setDrawCircleHole(true)
        lineDataSet.setDrawCircles(true)
        lineDataSet.setDrawHorizontalHighlightIndicator(false)
        lineDataSet.setDrawHighlightIndicators(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER

        val lineData = LineData(lineDataSet)

        val lineChart = binding.graph
        lineChart.data = lineData

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor = Color.BLACK
        xAxis.enableGridDashedLine(8f, 24f, 0f)

        val yLAxis = lineChart.axisLeft
        yLAxis.textColor = Color.BLACK

        val yRAxis = lineChart.axisRight
        yRAxis.setDrawLabels(false)
        yRAxis.setDrawAxisLine(false)
        yRAxis.setDrawGridLines(false)

        val description = Description()
        description.text = ""

        lineChart.isDoubleTapToZoomEnabled = false
        lineChart.setDrawGridBackground(false)
        lineChart.description = description
        lineChart.animateY(2000, Easing.EaseInCubic)
        lineChart.invalidate()
    }

    }


