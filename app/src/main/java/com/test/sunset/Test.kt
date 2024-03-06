package com.test.sunset

import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
import jp.wasabeef.glide.transformations.BlurTransformation


class Test : AppCompatActivity() {
   private lateinit var binding: com.test.sunset.databinding.Test2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Test2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val paint = Paint()
        paint.color = Color.RED
        paint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)

        binding.tvtvtv.paint.maskFilter = paint.maskFilter
        Glide.with(this)
            .load(R.drawable.sunsetimg) // 이미지 리소스나 URL
            .apply(RequestOptions.bitmapTransform(BlurTransformation(100))) // 블러 처리
            .into(binding.imageView5) // 이미지뷰에 설정
    }
}