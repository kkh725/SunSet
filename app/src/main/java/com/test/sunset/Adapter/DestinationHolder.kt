package com.test.sunset.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.test.sunset.itemss.DestinationInfo
import com.test.sunset.databinding.GridItemDestinationBinding

class DestinationHolder(private val binding: GridItemDestinationBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(destinationInfo: DestinationInfo){
        binding.destinationInfo = destinationInfo //데이터와 레이아웃을 연결시켜준다. 레이아웃의 destinationinfo는 뷰홀더에서 입력받는 destinationinfo라는점
    }

}