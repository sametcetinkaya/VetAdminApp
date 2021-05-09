package com.sametcetinkaya.vetadminapp.service

import com.sametcetinkaya.vetadminapp.model.CampaignAdd
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface VetAdminAPI {


    @GET("kampanya.php")
    fun getListCampaign() :Observable<List<CampaignAdd>>


    companion object {
        const val BASE_URL = "https://www.gamzesirakaya.com/veterinerservis/"
        fun getService(): VetAdminAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(VetAdminAPI::class.java)
        }
    }
}