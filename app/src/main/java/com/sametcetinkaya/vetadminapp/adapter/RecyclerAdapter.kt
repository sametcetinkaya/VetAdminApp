package com.sametcetinkaya.vetadminapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sametcetinkaya.vetadminapp.R
import com.sametcetinkaya.vetadminapp.model.CampaignAdd
import kotlinx.android.synthetic.main.recycler_row.view.*
import java.util.ArrayList

class RecyclerAdapter(var listCampaign: ArrayList<CampaignAdd>) :
    RecyclerView.Adapter<RecyclerAdapter.CampaignViewHolder>() {

    class CampaignViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {

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
        holder.itemView.campaignSubtitle.text = listCampaign[position].campaignTitle
        holder.itemView.campaignTitle.text = listCampaign[position].campaignTitle


    }

    override fun getItemCount(): Int {
        return listCampaign.size
    }


}