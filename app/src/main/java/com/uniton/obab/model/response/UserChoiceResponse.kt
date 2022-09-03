package com.uniton.obab.model.response

import com.google.gson.annotations.SerializedName

data class UserChoiceResponse(
    @SerializedName("responseCode")
    val responseCode: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Any?,
)
