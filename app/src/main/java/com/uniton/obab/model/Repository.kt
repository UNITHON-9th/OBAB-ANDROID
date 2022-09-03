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
    val inviteCode: String,
    @SerializedName("roomNo")
    val roomNo: String
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
data class PersonalResultRepository(
    @SerializedName("responseCode")
    val responseCode: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: PersonalResultData
)

data class PersonalResultData(
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
    @SerializedName("submitCount")
    val submitCount: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)