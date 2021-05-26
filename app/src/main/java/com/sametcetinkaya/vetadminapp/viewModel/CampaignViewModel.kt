package com.sametcetinkaya.vetadminapp.viewModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametcetinkaya.vetadminapp.model.ListCampaignModel
import com.sametcetinkaya.vetadminapp.service.VetAdminAPI
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CampaignViewModel : ViewModel() {
    private val DEBUG = true
    private val TAG: String = AddCampaignViewModel::class.java.simpleName
    val campaignList = MutableLiveData<List<ListCampaignModel>>()



    fun getCampaignList() {

        VetAdminAPI.getService().getCampaignData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<List<ListCampaignModel>> {
                override fun onSubscribe(d: Disposable) {
                    Log( "ServiceOnSubscribed")
                }

                override fun onNext(t: List<ListCampaignModel>) {
                    Log("ServiceOnNext"+t[0])
                    campaignList.value = t
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
            android.util.Log.d(TAG, message)

    }
}