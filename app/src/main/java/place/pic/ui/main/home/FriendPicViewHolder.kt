package place.pic.ui.main.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.ChipGroup
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 05일, 2020
 */
 
class FriendPicViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val profileImageUrl: ImageView = itemView.findViewById<ImageView>(R.id.fp_img_profile)
    val userName: TextView = itemView.findViewById<TextView>(R.id.fp_user_name)
    val part: TextView = itemView.findViewById<TextView>(R.id.fp_user_part)
    val imageUrl: ImageView = itemView.findViewById<ImageView>(R.id.fp_img)
    val name: TextView = itemView.findViewById<TextView>(R.id.fp_place_name) //식당 이름
    val subways: TextView = itemView.findViewById<TextView>(R.id.fp_subways) //여러 지하철역이 하나의 배열로 들어가
    //val tags = itemView.findViewById<ChipGroup>(R.id.fp_chipGroup) //ChipGroup으로 들어가
    val tags: TextView = itemView.findViewById<TextView>(R.id.fp_tags)
    val uploadDate: TextView = itemView.findViewById<TextView>(R.id.fp_upload_date)
    val content: TextView = itemView.findViewById<TextView>(R.id.fp_content)

    fun bind(friendPicData: FriendPicData) {
        Glide.with(itemView).load(friendPicData.profileImageUrl).into(profileImageUrl)
        userName.text = friendPicData.userName
        part.text = friendPicData.part
        Glide.with(itemView).load(friendPicData.imageUrl).into(imageUrl)
        name.text = friendPicData.name
        subways.text = friendPicData.subways.toString()
        tags.text = friendPicData.tags.toString()
        uploadDate.text = friendPicData.uploadDate.toString()
        content.text = friendPicData.content
    }
}