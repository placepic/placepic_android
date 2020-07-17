package place.pic.ui.main.detail.liker

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R
import place.pic.data.remote.response.Like
import place.pic.ui.util.unixDateTimeParser

class LikerUserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val userProfile = itemView.findViewById<ImageView>(R.id.user_image)
    val userName = itemView.findViewById<TextView>(R.id.user_name)
    val userPart = itemView.findViewById<TextView>(R.id.user_part)
    val userCreatedAt = itemView.findViewById<TextView>(R.id.user_create_at)

    fun bind(customData: Like) {
        Glide.with(itemView).load(customData.profileImageUrl).into(userProfile)
        userName.text = customData.userName
        userPart.text = customData.part
        userCreatedAt.text = unixDateTimeParser(customData.likeCreatedAt.toLong())
    }
}