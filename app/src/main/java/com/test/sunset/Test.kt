package com.test.sunset

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.transition.Transition
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.test.sunset.databinding.Test2Binding
import jp.wasabeef.glide.transformations.BlurTransformation


class Test : AppCompatActivity() {
    private lateinit var binding: com.test.sunset.databinding.Test2Binding
//master branch

    /**
     * clone 후 feature test 작업입니다.
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Test2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * blur ㅊㅓ리하는 코드
         */
//        val paint = Paint()
//        paint.color = Color.RED
//        paint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)
//
//        binding.tvtvtv.paint.maskFilter = paint.maskFilter
//        Glide.with(this)
//            .load(R.drawable.sunsetimg) // 이미지 리소스나 URL
//            .apply(RequestOptions.bitmapTransform(BlurTransformation(100))) // 블러 처리
//            .into(binding.imageView5) // 이미지뷰에 설정

        // 화면에 배경 블러 효과를 적용하기 위해 테마를 설정합니다.
        // Glide를 사용하여 이미지를 로드하고 블러 처리하여 이미지뷰의 배경으로 설정합니다.
        Glide.with(this)
            .load(R.drawable.ic_launcher_foreground) // 이미지 리소스
            .apply(RequestOptions.bitmapTransform(BlurTransformation(10))) // 블러 처리
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                ) {
                    val blurredBitmap = (resource as BitmapDrawable).bitmap
                    binding.imageView5.background = BitmapDrawable(resources, blurredBitmap)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // 이미지 로드가 취소되었을 때 수행할 작업
                }
            })


    }
}
