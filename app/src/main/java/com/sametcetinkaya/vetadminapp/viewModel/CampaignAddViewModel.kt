package com.sametcetinkaya.vetadminapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametcetinkaya.vetadminapp.model.CampaignAdd
import com.sametcetinkaya.vetadminapp.service.VetAdminAPI
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CampaignAddViewModel : ViewModel() {
    val campaignAdd = MutableLiveData<List<CampaignAdd>>()  //LiveData inner class


    fun campaignAdd() {
        VetAdminAPI.getService().getListCampaign()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<List<CampaignAdd>> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("CampaignOnSubscribed", "CampaignOnSubscribed")
                }

                override fun onNext(t: List<CampaignAdd>) {
                    Log.d("CampaignOnNext", "CampaignOnNext" + t[0].campaignText)
                }

                override fun onError(e: Throwable) {
                    Log.d("CampaignOnError", "CampaignOnNext" + e.message)
                }

                override fun onComplete() {
                    Log.d("CampaignOnComplete", "CampaignOnComplete")

                }


            })
    }


}