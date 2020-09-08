package place.pic.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import place.pic.R
import place.pic.ui.tag.ChipFactory

/**
 * Created By kimdahyee
 * on 09월 05일, 2020
 */

class FriendPicViewHolder(itemView: View, inflater: LayoutInflater) : RecyclerView.ViewHolder(itemView) {

    val profileImageUrl: ImageView = itemView.findViewById(R.id.fp_img_profile)
    val userName: TextView = itemView.findViewById(R.id.fp_user_name)
    val part: TextView = itemView.findViewById(R.id.fp_user_part)
    val imageUrl: ImageView = itemView.findViewById(R.id.fp_img)
    val liker: TextView = itemView.findViewById(R.id.fp_liker)
    val name: TextView = itemView.findViewById(R.id.fp_place_name)
    val subways: TextView = itemView.findViewById(R.id.fp_subways)
    val tagsChipGroup: ChipGroup = itemView.findViewById(R.id.fp_chipGroup)
    val uploadDate: TextView = itemView.findViewById(R.id.fp_upload_date)
    val content: TextView = itemView.findViewById(R.id.fp_content)

    private val inflater:LayoutInflater = inflater 
    //Chip 생성을 위한 inflater 준비

    fun bind(friendPicData: FriendPicData) {
        Glide.with(itemView).load(friendPicData.profileImageUrl).into(profileImageUrl)
        userName.text = friendPicData.userName
        part.text = friendPicData.part
        Glide.with(itemView).load(friendPicData.imageUrl).into(imageUrl)
        imageUrl.maxHeight = imageUrl.width / 2
        liker.text = friendPicData.liker.toString()
        name.text = friendPicData.name
        subways.text = friendPicData.subways.toString()
        uploadDate.text = friendPicData.uploadDate.toString()
        content.text = friendPicData.content

        val tags = friendPicData.tags
        tags.forEach { text ->
            val chip = ChipFactory.createDetailChip(inflater)
            //하나씩 chip 생성
            chip.text = text
            tagsChipGroup.addView(chip)
        }
    }
}