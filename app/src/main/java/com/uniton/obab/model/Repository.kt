package com.uniton.obab.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("responseCode")
    val responseCode: String,
    @SerializedName("message")
    val message: String
)
