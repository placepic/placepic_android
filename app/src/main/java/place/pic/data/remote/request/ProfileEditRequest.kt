package place.pic.data.remote.request

import okhttp3.MultipartBody
import place.pic.ui.upload.ImageUri

data class ProfileEditRequest(
    val part: String
) : BaseRequest<Unit>()
