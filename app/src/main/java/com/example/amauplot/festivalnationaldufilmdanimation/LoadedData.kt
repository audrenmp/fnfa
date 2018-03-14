package com.example.amauplot.festivalnationaldufilmdanimation

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

data class LoadedData(
        val id: Int,
        val title: String,
        val cat_id : Int,
        val location_id: Int,
        val image: Bitmap,
        val weekDay: String,
        val day: Int,
        val month: String,
        val startTime: Int,
        val mins: Int,
        val url: String,
        val age: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable<Bitmap>(Bitmap::class.java.classLoader),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        return
    }

    override fun describeContents(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<LoadedData> {
        override fun createFromParcel(parcel: Parcel): LoadedData {
            return LoadedData(parcel)
        }

        override fun newArray(size: Int): Array<LoadedData?> {
            return arrayOfNulls(size)
        }
    }
}