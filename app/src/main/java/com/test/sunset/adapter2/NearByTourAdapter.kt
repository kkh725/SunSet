package com.test.sunset.adapter2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.sunset.databinding.TourRegionItemBinding
import com.test.sunset.itemss.NearByTourInfo

class NearByTourAdapter(private var nearByTourInfolist:  MutableList<NearByTourInfo>, private val context: Context) : RecyclerView.Adapter<NearByTourHolder>() {
    private lateinit var  binding: TourRegionItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearByTourHolder {
        binding = TourRegionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NearByTourHolder(binding,context)
    }

    override fun onBindViewHolder(holder: NearByTourHolder, position: Int) {

        val nearbytourinfolist = nearByTourInfolist[position]
        holder.bind(nearbytourinfolist)
        //binding.view3.setOnClickListener{Log.d("position",position.toString())}
        //holder.itemView.setOnClickListener{Log.d("position",position.toString())} 해당 position의 아이템을 눌렀을때 작동.
    }

    override fun getItemCount(): Int {
        return nearByTourInfolist.size
    }
    fun setFilteredList(filteredList: MutableList<NearByTourInfo>) {
        this.nearByTourInfolist.clear() // 필터링된 목록을 초기화
        this.nearByTourInfolist.addAll(filteredList) // 새로운 목록으로 필터링된 목록을 설정
        notifyDataSetChanged() // 변경된 목록을 RecyclerView에 반영
    }

}