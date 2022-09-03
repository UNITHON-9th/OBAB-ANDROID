package com.uniton.obab.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("responseCode")
    val responseCode: String,
    @SerializedName("message")
    val message: String
)
data class CreateRoomRepository(
    @SerializedName("responseCode")
    val responseCode: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: CreateRoomData
)

data class CreateRoomData(
    @SerializedName("inviteCode")
    val inviteCode: String
)

data class EnterRoomRepository(
    @SerializedName("responseCode")
    val responseCode: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: EnterRoomData
)

data class EnterRoomData(
    @SerializedName("roomNo")
    val roomNo: String
)