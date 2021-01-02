package com.shaadicom.data


import com.google.gson.annotations.SerializedName

data class Registered(
    @SerializedName("age")
    val age_registered: Int,
    @SerializedName("date")
    val date_registered: String
)