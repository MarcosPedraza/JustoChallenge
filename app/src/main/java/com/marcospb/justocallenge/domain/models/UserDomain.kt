package com.marcospb.justocallenge.domain.models

import android.os.Parcel
import android.os.Parcelable

data class UserDomain(
    val photo:String,
    val username: String,
    val email: String,
    val phone: String,
    val dir: String,
    val city: String,
    val state:String,
    val postalCode: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(phone)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(dir)
        parcel.writeString(city)
        parcel.writeString(postalCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDomain> {
        override fun createFromParcel(parcel: Parcel): UserDomain {
            return UserDomain(parcel)
        }

        override fun newArray(size: Int): Array<UserDomain?> {
            return arrayOfNulls(size)
        }
    }
}
