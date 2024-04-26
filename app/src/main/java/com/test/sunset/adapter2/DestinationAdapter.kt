package com.test.sunset.adapter2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.sunset.MainActivityViewModel
import com.test.sunset.itemss.DestinationInfo
import com.test.sunset.databinding.GridItemDestinationBinding

class DestinationAdapter(
    private val DestinationList: List<DestinationInfo>,
    private val viewModel: MainActivityViewModel
) : RecyclerView.Adapter<DestinationHolder>() {
    private lateinit var  binding: GridItemDestinationBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationHolder {
        binding = GridItemDestinationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DestinationHolder(binding)
    }

    override fun getItemCount(): Int {
        return DestinationList.size
    }

    override fun onBindViewHolder(holder: DestinationHolder, position: Int) {
        val destinationlist = DestinationList[position]
        holder.bind(destinationlist,viewModel)
        //binding.view3.setOnClickListener{Log.d("position",position.toString())}
        //holder.itemView.setOnClickListener{Log.d("position",position.toString())} 해당 position의 아이템을 눌렀을때 작동.
    }

}