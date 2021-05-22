package com.sametcetinkaya.vetadminapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sametcetinkaya.vetadminapp.R
import com.sametcetinkaya.vetadminapp.model.CampaignAdd
import com.sametcetinkaya.vetadminapp.utils.imageDownload
import com.sametcetinkaya.vetadminapp.utils.placeHolderCreate
import kotlinx.android.synthetic.main.recycler_row.view.*
import java.util.ArrayList

class RecyclerAdapter(var listCampaign: ArrayList<CampaignAdd>) :
    RecyclerView.Adapter<RecyclerAdapter.CampaignViewHolder>() {

    class CampaignViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.recycler_row,
            parent,
            false
        )
        return CampaignViewHolder(view)


    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {

        holder.itemView.apply {
            campaignSubtitle.text = listCampaign[position].campaignText
            campaignTitle.text = listCampaign[position].campaignTitle
            Glide.with(this).load(listCampaign[position].campaignImage).into(campaignImage)
        }



    }

    override fun getItemCount(): Int {
        return listCampaign.size
    }

    fun campaignListUpdate(newCampaignList: List<CampaignAdd>) {
        listCampaign.addAll(newCampaignList)
        notifyDataSetChanged()
    }




}