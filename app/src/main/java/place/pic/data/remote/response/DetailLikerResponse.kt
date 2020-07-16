package place.pic.data.remote.response

import android.os.Parcel
import android.os.Parcelable

data class DetailLikerResponse(
    val likeList: List<Like>
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Like)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(likeList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailLikerResponse> {
        override fun createFromParcel(parcel: Parcel): DetailLikerResponse {
            return DetailLikerResponse(parcel)
        }

        override fun newArray(size: Int): Array<DetailLikerResponse?> {
            return arrayOfNulls(size)
        }
    }
}
