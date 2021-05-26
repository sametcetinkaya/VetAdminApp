package com.sametcetinkaya.vetadminapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametcetinkaya.vetadminapp.model.AddCampaignModel
import com.sametcetinkaya.vetadminapp.service.VetAdminAPI
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddCampaignViewModel : ViewModel() {
    private val DEBUG = true
    private val TAG: String = AddCampaignViewModel::class.java.simpleName
    val campaignAddData = MutableLiveData<AddCampaignModel>()



    fun getCampaignAdd(baslik: String, text: String, resim: String) {

        VetAdminAPI.getService().getAddCampaign(baslik, text, resim)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<AddCampaignModel> {
                override fun onSubscribe(d: Disposable) {
                    Log( "ServiceOnSubscribed")
                }

                override fun onNext(t: AddCampaignModel) {
                    Log("ServiceOnNext"+t.text)
                }

                override fun onError(e: Throwable) {
                    Log("ServiceOnError"+e.message)
                }

                override fun onComplete() {
                    Log("ServiceOnComplete")
                }

            })


    }
    private fun Log(message: String) {
        if (DEBUG)
            Log.d(TAG, message)
        
    }


}


