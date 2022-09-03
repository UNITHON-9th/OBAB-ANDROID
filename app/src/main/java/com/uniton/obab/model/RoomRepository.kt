package com.uniton.obab.model

import com.google.gson.annotations.SerializedName

data class RoomRepository(
    @SerializedName("responseCode")
    val responseCode: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Data
)

data class Data(
    @SerializedName("inviteCode")
    val inviteCode: String
)