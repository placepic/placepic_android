package place.pic.data.remote.response

import android.os.Parcel
import android.os.Parcelable

data class ResponseWaitUser(
    val gender: Int,
    val part: String?,
    val phoneNumber: String?,
    val userBirth: String?,
    val userName: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(gender)
        parcel.writeString(part)
        parcel.writeString(phoneNumber)
        parcel.writeString(userBirth)
        parcel.writeString(userName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseWaitUser> {
        override fun createFromParcel(parcel: Parcel): ResponseWaitUser {
            return ResponseWaitUser(parcel)
        }

        override fun newArray(size: Int): Array<ResponseWaitUser?> {
            return arrayOfNulls(size)
        }
    }
}