package com.sametcetinkaya.vetadminapp.service

import com.sametcetinkaya.vetadminapp.model.CampaignAdd
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface  VetAdminAPI {


    @GET("kampanya.php")
    fun getBaseData(): Single<List<CampaignAdd>>
}