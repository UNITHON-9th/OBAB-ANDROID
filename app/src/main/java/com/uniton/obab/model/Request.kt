package com.uniton.obab.model

import com.google.gson.annotations.SerializedName


data class CreateRoomRequest(
    @SerializedName("totalCount") var totalCount: Int
)

data class EnterRoomRequest(
    @SerializedName("deviceId") var deviceId: String,
    @SerializedName("inviteCode") var inviteCode: String
)
