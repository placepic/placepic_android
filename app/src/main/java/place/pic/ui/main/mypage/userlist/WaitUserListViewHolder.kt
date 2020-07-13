package place.pic.ui.main.mypage.userlist

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.data.remote.response.ResponseWaitUser

class WaitUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val userName = itemView.findViewById<TextView>(R.id.tv_wait_user_title)
    val userGroup = itemView.findViewById<TextView>(R.id.tv_wait_user_group)

    fun bind(customData: ResponseWaitUser) {
        userName.text = customData.userName
        userGroup.text = customData.part
        itemViewClickEvent(customData)
    }

    private fun itemViewClickEvent(customData: ResponseWaitUser) {
        itemView.setOnClickListener {
            val gotoUserInfoIntent = Intent(itemView.context,UserInfoActivity::class.java)
            gotoUserInfoIntent.putExtra("WaitUser",customData)
            itemView.context
                .startActivity(gotoUserInfoIntent)
        }
    }
}