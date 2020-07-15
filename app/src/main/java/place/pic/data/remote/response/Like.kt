package place.pic.data.remote.response

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Like(
    val likeCreatedAt: Int,
    val part: String,
    val profileImageUrl: String,
    val userName: String
):Serializable