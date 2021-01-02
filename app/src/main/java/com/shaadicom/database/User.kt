package com.shaadicom.database

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.shaadicom.data.*
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var user_id: Int=0,
    val cell: String,
    @Embedded val dob:Dob,
    val email: String,
    val gender: String,
    @Embedded val location: Location,
    @Embedded val name: Name,
    val nat: String,
    val phone: String,
    @Embedded val picture: Picture,
    val status: Int

):Serializable
