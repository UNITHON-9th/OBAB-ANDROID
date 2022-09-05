package com.uniton.obab.model.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("countries")
    val countries: List<Int>,
    @SerializedName("foods")
    val foods: List<Int>,
    @SerializedName("hots")
    val hots: List<Int>,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("soups")
    val soups: List<Int>,
    @SerializedName("spicys")
    val spicys: List<Int>,
    @SerializedName("totalCount")
    val totalCount: Int
)
