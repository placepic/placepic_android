package place.pic.data.remote.response

import android.os.Parcel
import android.os.Parcelable


/*
* "userIdx": 77,
            "username": "김다혜",
            "part": "서버",
            "userBirth": "19980521",
            "phoneNumber": "010-2222222",
            "gender": 1,
            "groupIdx": 3*/
data class ResponseWaitUser(
    val userIdx:Int,
    val userName: String?,
    val part: String?,
    val userBirth: String?,
    val phoneNumber: String?,
    val gender: Int,
    val groupIdx:Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userIdx)
        parcel.writeString(userName)
        parcel.writeString(part)
        parcel.writeString(userBirth)
        parcel.writeString(phoneNumber)
        parcel.writeInt(gender)
        parcel.writeInt(groupIdx)
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