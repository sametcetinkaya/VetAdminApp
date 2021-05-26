package com.sametcetinkaya.vetadminapp.model

import com.google.gson.annotations.SerializedName

data class DeleteCampaignModel (
    @SerializedName("id")
    val id :Int,
    @SerializedName("tf")
    val tf :Boolean
)
{

}