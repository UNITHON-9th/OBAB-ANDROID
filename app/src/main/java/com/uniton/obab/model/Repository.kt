package com.uniton.obab.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
)
