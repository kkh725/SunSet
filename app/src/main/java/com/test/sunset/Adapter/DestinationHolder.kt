package com.test.sunset.Adapter

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.test.sunset.itemss.DestinationInfo
import com.test.sunset.databinding.GridItemDestinationBinding

class DestinationHolder(private val binding: GridItemDestinationBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(destinationInfo: DestinationInfo){
        binding.destinationInfo = destinationInfo //데이터와 레이아웃을 연결시켜준다. 레이아웃의 destinationinfo는 뷰홀더에서 입력받는 destinationinfo라는점

        when(destinationInfo.type){ // 생성한 view 의 타입에 따른 alert변경

            "1" ->  binding.view3.setOnClickListener {
                    AlertDialog.Builder(binding.root.context)
                    .setTitle("주의")
                    .setMessage("이것은 타입1번의 view 입니다.\n")
                    .show()
            }
            "2" ->  binding.view3.setOnClickListener {
                AlertDialog.Builder(binding.root.context)
                    .setTitle("주의")
                    .setMessage("이것은 타입2번의 view 입니다.\n")
                    .show()
            }

        }


    }

}