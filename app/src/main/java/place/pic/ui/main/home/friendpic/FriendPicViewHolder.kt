package place.pic.ui.main.home.friendpic

import android.view.LayoutInflater
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

class FriendPicViewHolder(itemView: View, inflater: LayoutInflater) : RecyclerView.ViewHolder(itemView) {

    val profileImageUrl: ImageView = itemView.findViewById(R.id.fp_img_profile)
    val userName: TextView = itemView.findViewById(R.id.fp_user_name)
    val part: TextView = itemView.findViewById(R.id.fp_user_part)
    val placeName: TextView = itemView.findViewById(R.id.fp_place_name)
    val placeReview: TextView = itemView.findViewById(R.id.fp_content)
    private val placeImageUrl: ImageView = itemView.findViewById(R.id.fp_img)
    val placeCreatedAt: TextView = itemView.findViewById(R.id.fp_upload_date)
    val subway: TextView = itemView.findViewById(R.id.fp_subways)
    var tag: ChipGroup = itemView.findViewById(R.id.fp_chipGroup)
    private val likeCnt: TextView = itemView.findViewById(R.id.fp_liker)
    private val commentImage: ImageView = itemView.findViewById(R.id.img_reply_home)
    private val commentCnt: TextView = itemView.findViewById(R.id.tv_reply_home)

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

        if (friendPicData.commentCnt == 0) {
            commentImage.visibility = View.GONE
            commentCnt.visibility = View.GONE
        } else {
            commentCnt.text = friendPicData.commentCnt.toString()
        }

        tag.removeAllViews() //뷰가 재활용이 되기 때문에 지워줘야해
        val tags = friendPicData.tag
        var count = 0
        tags.forEach { text ->
            if (count < 3) {
                val chip = ChipFactory.createDetailChip(inflater)
                //하나씩 chip 생성
                chip.text = text
                tag.addView(chip)
                count++
            }
        }
    }
}