package place.pic.ui.main.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.ChipGroup
import place.pic.R
import place.pic.ui.tag.ChipFactory

/**
 * Created By kimdahyee
 * on 09월 05일, 2020
 */
 
class FriendPicViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val profileImageUrl = itemView.findViewById<ImageView>(R.id.fp_img_profile)
    val userName = itemView.findViewById<TextView>(R.id.fp_user_name)
    val part = itemView.findViewById<TextView>(R.id.fp_user_part)
    val imageUrl = itemView.findViewById<ImageView>(R.id.fp_img)
    val name = itemView.findViewById<TextView>(R.id.fp_place_name) //식당 이름
    val subways = itemView.findViewById<TextView>(R.id.fp_subways)
    //val keywordTags = itemView.findViewById<ChipGroup>(R.id.fp_keywords_chipGroup)
    val uploadDate = itemView.findViewById<TextView>(R.id.fp_upload_date)


    fun bind(friendPicData: FriendPicData) {
        Glide.with(itemView).load(friendPicData.profileImageUrl).into(profileImageUrl)
        userName.text = friendPicData.userName
        part.text = friendPicData.part
        Glide.with(itemView).load(friendPicData.imageUrl).into(imageUrl)
        name.text = friendPicData.name
        subways.text = friendPicData.subways.toString()
        //keywordTags.adapter =
        uploadDate.text = friendPicData.uploadDate.toString()
    }
}