package com.test.sunset.adapter2

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.test.sunset.R
import com.test.sunset.databinding.TourRegionItemBinding
import com.test.sunset.itemss.NearByTourInfo

class NearByTourHolder(
    private val binding: TourRegionItemBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(nearByTourInfo: NearByTourInfo) {
        binding.nearByTourDataInfo = nearByTourInfo

        itemView.setOnClickListener {

            val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_item, null)
            view.findViewById<TextView>(R.id.tv_region).text = nearByTourInfo.관광지명
            view.findViewById<TextView>(R.id.tv_address).text = nearByTourInfo.소재지지번주소
            view.findViewById<TextView>(R.id.tv_call).text = nearByTourInfo.관리기관전화번호
            view.findViewById<TextView>(R.id.tv_info).text = nearByTourInfo.관광지소개

            val alertDialogBuilder = AlertDialog.Builder(context,R.style.CustomAlertDialog)
                .setView(view)
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}