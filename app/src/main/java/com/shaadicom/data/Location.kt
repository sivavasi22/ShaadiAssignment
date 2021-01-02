package com.shaadicom.data


import androidx.room.Embedded
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

data class Location(
    val city: String,
    @Embedded val coordinates: Coordinates,
    val country: String,
    val state: String,
    @Embedded val street: Street,
    @Embedded val timezone: Timezone
)