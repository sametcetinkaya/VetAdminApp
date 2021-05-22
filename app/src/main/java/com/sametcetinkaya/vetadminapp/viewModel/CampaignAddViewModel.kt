package com.sametcetinkaya.vetadminapp.viewModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametcetinkaya.vetadminapp.model.CampaignAdd
import com.sametcetinkaya.vetadminapp.service.API
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CampaignAddViewModel : ViewModel() {
    private val dataApiService = API()
    val campaignAdd = MutableLiveData<List<CampaignAdd>>()  //LiveData inner class
    private val disposable = CompositeDisposable()

    fun refreshData(){
        campaignAddList()
    }
    private fun campaignAddList() {

        disposable.add(
            dataApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CampaignAdd>>(){
                    override fun onSuccess (t: List<CampaignAdd>){
                        campaignAdd.value = t
                        Log.d("asdasf","asfsaf"+t[0].campaignImage)
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        )
    }
}