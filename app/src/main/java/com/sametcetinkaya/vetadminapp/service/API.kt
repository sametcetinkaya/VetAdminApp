package com.sametcetinkaya.vetadminapp.service

import com.sametcetinkaya.vetadminapp.model.CampaignAdd
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class API {
    val BASE_URL = "https://www.gamzesirakaya.com/veterinerservis/"
    private val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(VetAdminAPI::class.java)

        fun getData() : Single<List<CampaignAdd>>{
            return api.getBaseData()
        }
    }

