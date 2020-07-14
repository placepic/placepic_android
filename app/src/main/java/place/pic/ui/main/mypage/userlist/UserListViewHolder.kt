package place.pic.ui.main.mypage.userlist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */

class UserListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    //private val img = itemView.findViewById<ImageView>(R.id.user_image)
    private val userName = itemView.findViewById<TextView>(R.id.user_name)
    private val part = itemView.findViewById<TextView>(R.id.user_part)
    private val postCount = itemView.findViewById<TextView>(R.id.user_post_count)

    fun bind(userData : UserData) {
        //Glide.with(itemView).load(userListData.img).into(img)
        userName.text = userData.userName
        part.text = userData.part
        postCount.text = userData.postCount.toString()
    }
}