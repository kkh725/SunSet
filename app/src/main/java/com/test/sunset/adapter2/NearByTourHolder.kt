package com.test.sunset.adapter2

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.sunset.R
import com.test.sunset.databinding.TourRegionItemBinding
import com.test.sunset.itemss.NearByTourInfo

class NearByTourHolder(
    private val binding: TourRegionItemBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(nearByTourInfo: NearByTourInfo) {
        binding.nearByTourDataInfo = nearByTourInfo

//        binding.imageView7.setBackgroundResource(nearByTourInfo.imgResourceId)
        val url = "https://images.unsplash.com/photo-1523731407965-2430cd12f5e4?q=80&w=2940&auto=format&" +
                "fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" //load 에 url 이 들어갈수있다.
        Glide.with(itemView).
        load(nearByTourInfo.imgResourceId).
        fitCenter().
        into(binding.imageView7)

        itemView.setOnClickListener {


            val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_item, null)
            view.findViewById<TextView>(R.id.tv_region).text = nearByTourInfo.관광지명
            view.findViewById<TextView>(R.id.tv_address).text = nearByTourInfo.소재지지번주소
            view.findViewById<TextView>(R.id.tv_call).text = nearByTourInfo.관리기관전화번호
            view.findViewById<TextView>(R.id.tv_info).text = nearByTourInfo.관광지소개

            val alertDialogBuilder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
                .setView(view)
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}

