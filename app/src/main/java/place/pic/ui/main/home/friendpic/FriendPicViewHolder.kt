package place.pic.ui.main.home.friendpic

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.item_friend_pic.view.*
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
    val placeName: TextView = itemView.findViewById(R.id.fp_place_name)
    val placeReview: TextView = itemView.findViewById(R.id.fp_content)
    val placeImageUrl: ImageView = itemView.findViewById(R.id.fp_img)
    val placeCreatedAt: TextView = itemView.findViewById(R.id.fp_upload_date)
    val subway: TextView = itemView.findViewById(R.id.fp_subways)
    val tag: ChipGroup = itemView.findViewById(R.id.fp_chipGroup)
    val likeCnt: TextView = itemView.findViewById(R.id.fp_liker)

    private val inflater:LayoutInflater = inflater 
    //Chip 생성을 위한 inflater 준비

    private fun detailStringForm(stringList: List<String>, dividerString: String): String {
        val detailStringBuilder = StringBuilder("")

        stringList.forEach { str ->
            if (str != stringList[0]) {
                detailStringBuilder.append(dividerString)
                detailStringBuilder.append(str)
            } else {
                detailStringBuilder.append(str)
            }
        }
        return detailStringBuilder.toString()
    }

    fun bind(friendPicData: FriendPicData) {
        Glide.with(itemView).load(friendPicData.profileImageUrl).into(profileImageUrl)
        userName.text = friendPicData.userName
        part.text = friendPicData.part
        placeImageUrl.maxHeight = placeImageUrl.width / 2
        placeName.text = friendPicData.placeName
        placeReview.text = friendPicData.placeReview
        Glide.with(itemView).load(friendPicData.placeImageUrl).into(placeImageUrl)
        placeCreatedAt.text = friendPicData.placeCreatedAt.toString()
        subway.text = detailStringForm(friendPicData.subway, "/")
        likeCnt.text = friendPicData.likeCnt.toString()

        /*var tags= friendPicData.tag
        Log.d("tag check", tags.toString())
        tags.forEach { text ->
            val chip = ChipFactory.createDetailChip(inflater)
            //하나씩 chip 생성
            chip.text = text
            tag.addView(chip)
        }*/
    }
}