package com.uniton.obab.model

import com.google.gson.annotations.SerializedName

data class RoomRequest(
    @SerializedName("totalCount") var totalCount: Int
)
