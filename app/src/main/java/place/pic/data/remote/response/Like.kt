package place.pic.data.remote.response

import android.os.Parcel
import android.os.Parcelable

data class Like(
    val likeCreatedAt: Int,
    val part: String,
    val profileImageUrl: String,
    val userName: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(likeCreatedAt)
        parcel.writeString(part)
        parcel.writeString(profileImageUrl)
        parcel.writeString(userName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Like> {
        override fun createFromParcel(parcel: Parcel): Like {
            return Like(parcel)
        }

        override fun newArray(size: Int): Array<Like?> {
            return arrayOfNulls(size)
        }
    }
}