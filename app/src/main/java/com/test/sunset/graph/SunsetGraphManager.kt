package com.test.sunset.graph

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.MotionEvent
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.test.sunset.R
import com.test.sunset.databinding.ActivityMainBinding

class SunsetGraphManager(private val binding: ActivityMainBinding,
                         private val sunset : Pair<Int,Int>,
                         private val sunrise : Pair<Int,Int>,
                         private val noontime : Pair<Int,Int>) {

    fun makeSunsetGraph(){

        val sunriseTime = sunrise.first * 60 + sunrise.second // 일출 시각을 분으로 변환
        val noonTime = (noontime.first+12) * 60 + noontime.second // 정오 시각을 분으로 변환
        val sunsetTime = (sunset.first+12) * 60 + sunset.second // 일몰 시각을 분으로 변환

        val lineChart = binding.graph

        //entry 추가
        val sunriseEntry = Entry(sunriseTime.toFloat(), 1.001f)
        val noonEntry = Entry(noonTime.toFloat(), 3f) // 예시로 정오 시각을 강조합니다.
        val sunsetEntry = Entry(sunsetTime.toFloat(), 1.002f)


        val entries = ArrayList<Entry>()
        entries.add(Entry(0f,0f))
        entries.add(sunriseEntry)
        entries.add(noonEntry) // 정오 시각을 추가합니다.
        entries.add(sunsetEntry)
        entries.add(Entry(1500f,0f))

        val yAxis: YAxis = lineChart.axisLeft
        //왼쪽 y축 limit 라인이라는게 따로 차트에 선을 그리는작업인듯
        val limitLine = LimitLine(1f, "") // 선의 위치를 yValue로 설정합니다. 그리고 레이블설정. 설명
        limitLine.lineWidth = 3f //선의 두께
        limitLine.lineColor = R.color.white
        limitLine.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM //선의 오른쪽아래에 레이블추가
        limitLine.textSize = 8f //레이블텍스트사이즈
        yAxis.addLimitLine(limitLine)


        //x축의 데이터표시값 포매팅
        val xAxis = lineChart.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                // 분을 시간과 분으로 변환하여 반환합니다.
                val hour = value.toInt() / 60
                val minute = value.toInt() % 60
                return String.format("%02d시", hour, minute)
            }
        }
        //x축을 화면 하단에 표시
        xAxis.position = XAxis.XAxisPosition.BOTTOM


        val yRAxis = lineChart.axisRight
        //오른쪽 Y 축 숫자값 표시 x
        yRAxis.setDrawLabels(false)

        val yLAxis = lineChart.axisLeft
        yLAxis.setDrawLabels(false)

        // 그래프 그리기
        //line dataset이 데이터 그래프자체를 설정할수있는 기능.
        //line dataset은 기본적으로 y값을 출력하게되어있다.
        //linedataset.setdrawvalues -> y값을 포인트아래에 출력시킴.
        //y값은 어차피 포인트를 강조하는데 사용되므로, 일출값을 1.001, 일몰을 1.002, 정오를 3.0으로 표시하여
        //이 y값에 따라 일출, 일몰, 정오값을 그래프에 표시한다.

        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.lineWidth = 3f //선의 두께
        lineDataSet.valueTextSize = 10f // 데이터의 text size
        lineDataSet.circleRadius = 3.5f //포인트의 반지름
        lineDataSet.setDrawCircleHole(true) //점을 채울건지 말건지
        val colorsList = arrayListOf(Color.BLACK)
        lineDataSet.circleColors = colorsList
        lineDataSet.color = Color.parseColor("#000000") //그래프 선의 색

        //value formatter를 사용해서 일몰일출정오 시간값을 그래프위에 표시
        lineDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                Log.d("value",value.toString())
                return when (value) {
                    1.001F -> "일출"
                    3.0f -> "정오"
                    1.002F -> "일몰"
                    else -> ""
                }
            }
        }

        lineDataSet.setDrawValues(true)
        val lineData = LineData(lineDataSet)

        val description = Description()
        description.text = "일몽일몽"
        description.textColor =  Color.parseColor("#CBD51515")
        description.typeface = Typeface.DEFAULT_BOLD


        //커스텀 마커뷰를 만들어서 마커를 추가함. (터치시 마커 사용되게)
        val markerView = CustomMarkerView(binding.root.context, R.layout.custom_marker_view)
        lineChart.marker = markerView

        lineChart.setScaleEnabled(false)
        lineChart.setPinchZoom(true)
        lineChart.description = description
        lineChart.data = lineData
        lineChart.animateY(2000, Easing.EaseInCubic)
        lineChart.invalidate()
    }
}