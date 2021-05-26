package com.sametcetinkaya.vetadminapp.service

import com.sametcetinkaya.vetadminapp.model.AddCampaignModel
import com.sametcetinkaya.vetadminapp.model.DeleteCampaignModel
import com.sametcetinkaya.vetadminapp.model.ListCampaignModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface  VetAdminAPI {


    @GET("kampanya.php")
    fun getCampaignData(): Observable<List<ListCampaignModel>>

    @FormUrlEncoded
    @POST("kampanyaekle.php")
    fun getAddCampaign(
        @Field("baslik") baslik: String,
        @Field("text") text: String,
        @Field("resim") resim: String
    ): Observable<AddCampaignModel>

    @FormUrlEncoded
    @POST("kampanyasil.php")
    fun getDeleteCampaign(
        @Field("id") id: Int
    ): Observable<DeleteCampaignModel>

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