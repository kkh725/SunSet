package com.test.sunset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.test.sunset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.myViewModel = viewModel
        binding.lifecycleOwner = this

        setupObserver()

        viewModel.fetchSunset("36.720","-4.420")
    }

    private fun setupObserver(){
        viewModel.sunset.observe(this) { sunset ->
            // 일몰 정보가 업데이트되면 이 곳에서 처리하세요.
            binding.tv1.text = sunset
        }
    }
}