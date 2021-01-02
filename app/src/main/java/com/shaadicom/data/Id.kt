package com.shaadicom.data


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("name")
    val name_Id: String,
    @SerializedName("value")
    val value: String
)