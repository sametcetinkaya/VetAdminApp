package com.sametcetinkaya.vetadminapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sametcetinkaya.vetadminapp.R
import com.sametcetinkaya.vetadminapp.model.DeleteCampaignModel
import com.sametcetinkaya.vetadminapp.model.ListCampaignModel
import com.sametcetinkaya.vetadminapp.service.VetAdminAPI
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.campaign_card.view.*
import java.util.ArrayList

class CampaignAdapter(var listCampaign: ArrayList<ListCampaignModel>) :
    RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder>() {

    private lateinit var deleteCampaign : DeleteCampaignModel
    class CampaignViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.campaign_card,
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
            deleteCampaignButton.setOnClickListener{
                deleteCampaign(
                    listCampaign[position].campaignId.toInt()
                )

            }
        }


    }

    override fun getItemCount(): Int {
        return listCampaign.size
    }

    fun campaignListUpdate(newCampaignList: List<ListCampaignModel>) {
        listCampaign.clear()
        listCampaign.addAll(newCampaignList)
        notifyDataSetChanged()
    }
    private fun deleteCampaign(id: Int) {

        VetAdminAPI.getService().getDeleteCampaign(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<DeleteCampaignModel> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("ServiceOnSubscribed", "ServiceOnSubscribed")
                }

                override fun onNext(t: DeleteCampaignModel) {
                    deleteCampaign = t

                }

                override fun onError(e: Throwable) {
                    Log.d("ServiceOnError", "ServiceOnError" + e.message)
                }

                override fun onComplete() {
                    Log.d("ServiceOnComplete", "ServiceOnComplete")
                }

            })


    }




}