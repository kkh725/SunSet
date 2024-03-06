package com.test.sunset.graph

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

//chart 안에 포인트를 클릭할 시에 해당포인트의 정보를 표시하는 class
@SuppressLint("ViewConstructor")
class CustomMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {
    private val textView: TextView
    private val imageView : ImageView

    init {
        // MarkerView 내에 정의된 레이아웃 리소스를 inflate하여 레이아웃을 가져옵니다.
        val view = LayoutInflater.from(context).inflate(layoutResource, this, true)

        // 가져온 레이아웃에서 TextView와 ImageView를 찾아 초기화합니다.
        textView = TextView(context)
        imageView = ImageView(context)
    }

    // MarkerView에 텍스트를 설정하는 메서드입니다.
    fun setContent(text: String) {
        textView.text = text
    }
    fun setIcon(resourceId: Int) {
        imageView.setImageResource(resourceId)
    }
    // MarkerView가 업데이트될 때 호출되는 메서드입니다.


    // 특정 포인트가 선택되었을 때 호출되어 해당 포인트의 정보를 표시합니다.
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        val sunriseTime = e?.x?.toInt() ?: 0
        val hour = sunriseTime / 60
        val minute = sunriseTime % 60
        val timeText = String.format("%02d:%02d", hour, minute)
        setContent("시각: $timeText")
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}