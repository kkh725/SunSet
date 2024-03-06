package com.test.sunset.graph

import android.content.Context
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class TimeAxisValueFormatter(private val context: Context) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.toInt()}시"
    }

    override fun getFormattedValue(value: Float): String {
        // 여기에 원하는 값 포맷을 구현하세요.
        return "Custom Value"
    }
}