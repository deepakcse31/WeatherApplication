package com.example.weatherapplication.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

data class Weatherresponse  (
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        TODO("clouds"),
        parcel.readInt(),
        TODO("coord"),
        parcel.readInt(),
        parcel.readInt(),
        TODO("main"),
        parcel.readString()!!,
        TODO("sys"),
        parcel.readInt(),
        parcel.readInt(),
        TODO("weather"),
        TODO("wind")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(base)
        parcel.writeInt(cod)
        parcel.writeInt(dt)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(timezone)
        parcel.writeInt(visibility)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Weatherresponse> {
        override fun createFromParcel(parcel: Parcel): Weatherresponse {
            return Weatherresponse(parcel)
        }

        override fun newArray(size: Int): Array<Weatherresponse?> {
            return arrayOfNulls(size)
        }
    }

}