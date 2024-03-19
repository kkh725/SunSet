package com.test.sunset.adapter

import androidx.recyclerview.widget.RecyclerView
import com.test.sunset.databinding.TourRegionItemBinding
import com.test.sunset.itemss.NearByTourInfo

class NearByTourHolder(private val binding : TourRegionItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(nearByTourInfo: NearByTourInfo) {
        binding.nearByTourDataInfo =
            nearByTourInfo //데이터와 레이아웃을 연결시켜준다.
            //recyclerview의 데이터가 하나씩 쌓일때마다 생성. nearbytourdatainfo와 연결



    }
}

