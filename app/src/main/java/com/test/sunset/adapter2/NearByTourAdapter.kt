package com.test.sunset.adapter2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.test.sunset.databinding.TourRegionItemBinding
import com.test.sunset.itemss.NearByTourInfo

class NearByTourAdapter(private var nearByTourInfolist:  List<NearByTourInfo>, private val context: Context) : RecyclerView.Adapter<NearByTourHolder>() {
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

    private fun setItems(newItems: List<NearByTourInfo>) {
        this.nearByTourInfolist = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        var filteredList : MutableList<NearByTourInfo> = mutableListOf()

            if (query.isBlank()) {
            // 쿼리가 비어있으면 모든 항목을 보여줍니다.
            filteredList = nearByTourInfolist.toMutableList()
        }
            else {
            // 쿼리가 비어있지 않으면 적절히 필터링합니다.
            for (i in nearByTourInfolist){
                Log.d("item",i.관광지명)
                Log.d("item",query)
                if (i.관광지명.contains(query)){
                    filteredList.add(i)
                    Log.d("item","yes!!")
                }
//                else{
//                    filteredList.add(i)
//                }
            }

        }
        Log.d("itemsize",nearByTourInfolist[0].관광지명)
        Log.d("itemsize",filteredList.size.toString())

        // 필터링된 목록을 적용합니다.
        setItems(filteredList)
    }


}