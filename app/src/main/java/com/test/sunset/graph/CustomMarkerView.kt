package com.test.sunset.graph

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.test.sunset.R

//chart 안에 포인트를 클릭할 시에 해당포인트의 정보를 표시하는 class
@SuppressLint("ViewConstructor")
class CustomMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {
    private val textView: TextView
    private val imageView : ImageView

    init {
        // MarkerView 내에 정의된 레이아웃 리소스를 inflate하여 레이아웃을 가져옵니다.
        val view = LayoutInflater.from(context).inflate(layoutResource, this, true)

        // 가져온 레이아웃에서 TextView와 ImageView를 찾아 초기화합니다.
        textView = view.findViewById(R.id.tvContent)
        imageView = view.findViewById(R.id.imageView4)

    }

    override fun draw(canvas: Canvas?, posX: Float, posY: Float) {
        // 마커가 그려질 때 호출되는 로직을 구현합니다.
        super.draw(canvas, posX, posY)


    }


    // MarkerView가 업데이트될 때 호출되는 메서드입니다.
    // 특정 포인트가 선택되었을 때 호출되어 해당 포인트의 정보를 표시합니다.
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        val sunriseTime = e?.x?.toInt() ?: 0
        val hour = sunriseTime / 60
        val minute = sunriseTime % 60
        val timeText = String.format("%02d:%02d", hour, minute)

        ("시각: $timeText").also { textView.text = it }
        imageView.setImageResource(R.color.custom_yello)
        super.refreshContent(e, highlight)
    }

    //포인트를 클릭했을때 정보를 표시할 위치.
    //현재는 하단에 표시함.
    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), height.toFloat()/2)
    }
}