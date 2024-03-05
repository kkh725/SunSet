package com.test.sunset

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.test.sunset.databinding.Test2Binding


class Test : AppCompatActivity() {
   private lateinit var binding: com.test.sunset.databinding.Test2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Test2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val sunriseTime = 6 * 60 * 60 + 10 * 60 // 일출 시각을 초로 변환
        val sunsetTime = 18 * 60 * 60 + 32 * 60 + 5 // 일몰 시각을 초로 변환

        val entries = ArrayList<com.github.mikephil.charting.data.Entry>()
        entries.add(com.github.mikephil.charting.data.Entry(0f, 0f))
        entries.add(com.github.mikephil.charting.data.Entry(1f, 1f))
        entries.add(com.github.mikephil.charting.data.Entry(3f, 5f))
        entries.add(com.github.mikephil.charting.data.Entry(10f, 5f))
        entries.add(com.github.mikephil.charting.data.Entry(15f, 0f))

        val lineDataSet = LineDataSet(entries,"속성명1")
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

        val lineChart = binding.chart
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