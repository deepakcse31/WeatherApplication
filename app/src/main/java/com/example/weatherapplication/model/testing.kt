package com.example.weatherapplication.model

import android.os.Parcel
import android.os.Parcelable

data class testing(var name : String,var mobile : String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(mobile)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<testing> {
        override fun createFromParcel(parcel: Parcel): testing {
            return testing(parcel)
        }

        override fun newArray(size: Int): Array<testing?> {
            return arrayOfNulls(size)
        }
    }
}

