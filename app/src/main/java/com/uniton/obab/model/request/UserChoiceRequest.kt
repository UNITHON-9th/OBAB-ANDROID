package com.uniton.obab.model.request

import com.google.gson.annotations.SerializedName

data class UserChoiceRequest(
    @SerializedName("deviceId")
    val deviceId: String,
    @SerializedName("roomNo")
    val roomNo: String,
    @SerializedName("country")
    val country: Int,
    @SerializedName("food")
    val food: Int,
    @SerializedName("isSpicy")
    val isSpicy: Boolean,
    @SerializedName("isSoup")
    val isSoup: Boolean,
    @SerializedName("isHot")
    val isHot: Boolean,
)
