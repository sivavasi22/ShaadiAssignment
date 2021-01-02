package com.shaadicom.data


import com.google.gson.annotations.SerializedName

data class ShaadiData(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)