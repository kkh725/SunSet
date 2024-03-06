package com.test.sunset

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.test.sunset.databinding.Test2Binding
import com.test.sunset.graph.CustomMarkerView


class Test : AppCompatActivity() {
   private lateinit var binding: com.test.sunset.databinding.Test2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Test2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val lineChart = binding.chart


//        val sunriseTime = 6 * 60 * 60 + 10 * 60 // 일출 시각을 초로 변환
//        val sunsetTime = 18 * 60 * 60 + 32 * 60 + 5 // 일몰 시각을 초로 변환
//
//        val entries = ArrayList<com.github.mikephil.charting.data.Entry>()
//        entries.add(com.github.mikephil.charting.data.Entry(0f, 0f))
//        entries.add(com.github.mikephil.charting.data.Entry(1f, 1f))
//        entries.add(com.github.mikephil.charting.data.Entry(3f, 5f))
//        entries.add(com.github.mikephil.charting.data.Entry(10f, 5f))
//        entries.add(com.github.mikephil.charting.data.Entry(15f, 0f))
//
//        val lineDataSet = LineDataSet(entries,"속성명1")
//        lineDataSet.lineWidth = 2f
//        lineDataSet.circleRadius = 6f
//        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC")) //그래프의 점 색
//        lineDataSet.circleHoleColor = R.color.custom_yello
//        lineDataSet.color = Color.parseColor("#FFA1B4DC") //그래프 선의 색
//        lineDataSet.setDrawCircleHole(true)
//        lineDataSet.setDrawCircles(true)
//        lineDataSet.setDrawHorizontalHighlightIndicator(false)
//        lineDataSet.setDrawHighlightIndicators(false)
//        lineDataSet.setDrawValues(false)
//        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER //그래프의 형태
//
//        val lineData = LineData(lineDataSet)
//
//        val lineChart = binding.chart
//        lineChart.data = lineData
//
//        val xAxis = lineChart.xAxis
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.textColor = Color.BLACK
//        xAxis.valueFormatter = TimeAxisValueFormatter(binding.root.context) //entry 값들을 x시 로 변환
//        xAxis.enableGridDashedLine(8f, 24f, 0f)
//
//        val yLAxis = lineChart.axisLeft
//        yLAxis.textColor = Color.BLACK
//
//        val yRAxis = lineChart.axisRight
//        yRAxis.setDrawLabels(false)
//        yRAxis.setDrawAxisLine(false)
//        yRAxis.setDrawGridLines(false)
//
//        val yAxis: YAxis = lineChart.axisLeft //왼쪽 y축 limit 라인이라는게 따로 차트에 선을 그리는작업인듯
//        val limitLine = LimitLine(3f, "일몰일출이 쳐맞는 시각") // 선의 위치를 yValue로 설정합니다. 그리고 레이블설정. 설명
//        limitLine.lineWidth = 3f //선의 두께
//        limitLine.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM //선의 오른쪽아래에 레이블추가
//        limitLine.textSize = 10f //레이블텍스트사이즈
//        yAxis.addLimitLine(limitLine)
//
//        val description = Description()
//        description.text = "24시 그래프"
//
//        lineChart.isDoubleTapToZoomEnabled = false
//        lineChart.setDrawGridBackground(false)
//        lineChart.description = description
//        lineChart.animateY(2000, Easing.EaseInCubic)
//        lineChart.invalidate()



//        val lineChart = binding.chart
//

//
//        val markerView = CustomMarkerView(this, R.layout.custom_marker_view)
//        lineChart.marker = markerView
//
//        val totalMinutes = sunsetTime - sunriseTime // 일출부터 일몰까지의 총 분 수
//        val dataPoints = 100 // 그래프에 표시할 데이터 포인트 수
//
//        val sunriseEntry = Entry(sunriseTime.toFloat(), 0f)
//
//        markerView.setContent("일출시각: 06:32")
//
//        val entries = ArrayList<Entry>()
//        entries.add(sunriseEntry)
//
//
//// 일출부터 정오까지의 시간 동안 그래프가 상승하도록 설정
//        for (i in 0 until dataPoints / 2) {
//            val time = sunriseTime + i * totalMinutes / dataPoints
//            val yValue = i.toFloat() * i.toFloat() / (dataPoints / 2) // 제곱 함수를 사용하여 그래프를 조정합니다.
//            entries.add(Entry(time.toFloat(), yValue))
//        }
//
//// 정오부터 일몰까지의 시간 동안 그래프가 하강하도록 설정
//        for (i in dataPoints / 2 until dataPoints) {
//            val time = noonTime + (i - dataPoints / 2) * totalMinutes / dataPoints
//            val yValue = (dataPoints - i).toFloat() * (dataPoints - i).toFloat() / (dataPoints / 2) // 제곱 함수를 사용하여 그래프를 조정합니다.
//            entries.add(Entry(time.toFloat(), yValue))
//        }
//
//        val xAxis = lineChart.xAxis
//        xAxis.valueFormatter = object : ValueFormatter() {
//            override fun getFormattedValue(value: Float): String {
//                // 분을 시간과 분으로 변환하여 반환합니다.
//                val hour = value.toInt() / 60
//                val minute = value.toInt() % 60
//                return String.format("%02d:%02d", hour, minute)
//            }
//        }
//        val yRAxis = lineChart.axisRight
//        yRAxis.setDrawLabels(false)
//
//        val yLAxis = lineChart.axisLeft
//        yLAxis.setDrawLabels(false)
//
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//
//// 그래프 끝점 추가
//        entries.add(Entry(sunsetTime.toFloat(), 0f))
//
//// 그래프 그리기
//        val lineDataSet = LineDataSet(entries, "")
//
//        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER // 둥근 모양의 그래프로 설정합니다.
//        val lineData = LineData(lineDataSet)
//
//
//
//        lineChart.data = lineData
//        lineChart.invalidate()

        val sunriseTime = 6 * 60 + 32 // 일출 시각을 분으로 변환
        val noonTime = 13 * 60 + 10 // 정오 시각을 분으로 변환
        val sunsetTime = 18 * 60 + 45 // 일몰 시각을 분으로 변환

        //커스텀 마커뷰를 만들어서 마커를 추가함. (터치시 마커 사용되게)
        val markerView = CustomMarkerView(this, R.layout.custom_marker_view)
        lineChart.marker = markerView

        //entry 추가
        val sunriseEntry = Entry(sunriseTime.toFloat(), 1.001f)
        val noonEntry = Entry(noonTime.toFloat(), 3f) // 예시로 정오 시각을 강조합니다.
        val sunsetEntry = Entry(sunsetTime.toFloat(), 1.002f)

        val entries = ArrayList<Entry>()
        entries.add(Entry(0f,0f))
        entries.add(sunriseEntry)
        entries.add(noonEntry) // 정오 시각을 추가합니다.
        entries.add(sunsetEntry)
        entries.add(Entry(1300f,0f))

        val yAxis: YAxis = lineChart.axisLeft
        //왼쪽 y축 limit 라인이라는게 따로 차트에 선을 그리는작업인듯
        val limitLine = LimitLine(1f, "일몽일몽") // 선의 위치를 yValue로 설정합니다. 그리고 레이블설정. 설명
        limitLine.lineWidth = 3f //선의 두께
        limitLine.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM //선의 오른쪽아래에 레이블추가
        limitLine.textSize = 10f //레이블텍스트사이즈
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

// 그 외의 데이터 포인트를 추가합니다.
// 이 예시에서는 그래프가 규칙적으로 상승하고 하강하도록 설정되어 있습니다.

        // 그래프 그리기
        //line dataset이 데이터 그래프자체를 설정할수있는 기능.
        //line dataset은 기본적으로 y값을 출력하게되어있다.
        //linedataset.setdrawvalues -> y값을 포인트아래에 출력시킴.
        //y값은 어차피 포인트를 강조하는데 사용되므로, 일출값을 1.001, 일몰을 1.002, 정오를 3.0으로 표시하여
        //이 y값에 따라 일출, 일몰, 정오값을 그래프에 표시한다.

        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.lineWidth = 3f //선의 두께
        lineDataSet.valueTextSize = 13f // 데이터의 text size
        lineDataSet.circleRadius = 3f //포인트의 반지름
        lineDataSet.setDrawCircleHole(true) //점을 채울건지 말건지
        val colorsList = arrayListOf(Color.RED)
        lineDataSet.circleColors = colorsList
        lineDataSet.color = Color.parseColor("#FFA1B4DC") //그래프 선의 색

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

        Description().isEnabled=false

        lineChart.data = lineData
        lineChart.invalidate()
    }
}