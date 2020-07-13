package place.pic.ui.main.mypage.userlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */

class UserListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val userName = itemView.findViewById<TextView>(R.id.user_name)
    private val part = itemView.findViewById<TextView>(R.id.user_part)
    private val postCount = itemView.findViewById<TextView>(R.id.user_post_count)

    fun bind(userListData : UserListData) {
        userName.text = userListData.userName
        part.text = userListData.part
        postCount.text = userListData.postCount.toString()
    }
}