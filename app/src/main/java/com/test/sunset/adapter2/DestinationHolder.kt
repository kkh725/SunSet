package com.test.sunset.adapter2

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.test.sunset.AllTourRecommend
import com.test.sunset.MainActivityViewModel
import com.test.sunset.RecommendTourRegion
import com.test.sunset.itemss.DestinationInfo
import com.test.sunset.databinding.GridItemDestinationBinding

class DestinationHolder(private val binding: GridItemDestinationBinding) : RecyclerView.ViewHolder(binding.root) {
 

    fun bind(destinationInfo: DestinationInfo,viewModel: MainActivityViewModel) {
        binding.destinationInfo =
            destinationInfo //데이터와 레이아웃을 연결시켜준다. 레이아웃의 destinationinfo는 뷰홀더에서 입력받는 destinationinfo라는점

        when (destinationInfo.type) { // 생성한 view 의 타입에 따른 alert변경

            "1" -> binding.card.setOnClickListener {
                    val intent = Intent(binding.root.context, AllTourRecommend::class.java)
                intent.putExtra("region2", viewModel.region.value)
                    binding.root.context.startActivity(intent)

            }

            "2" -> binding.card.setOnClickListener {
                val intent = Intent(binding.root.context, RecommendTourRegion::class.java)
                intent.putExtra("region", viewModel.region.value)
                binding.root.context.startActivity(intent)
            }

        }


    }
}

