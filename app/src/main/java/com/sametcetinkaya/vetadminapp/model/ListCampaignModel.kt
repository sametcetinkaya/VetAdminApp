package com.sametcetinkaya.vetadminapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class ListCampaignModel (

    @SerializedName("id")
    val campaignId : String,
    @SerializedName("text")
    val campaignText : String,
    @SerializedName("resim")
    val campaignImage : String,
    @SerializedName("baslik")
    val campaignTitle : String
    )