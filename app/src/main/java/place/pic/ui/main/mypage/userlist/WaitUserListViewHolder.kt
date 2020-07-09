package place.pic.ui.main.mypage.userlist

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

class WaitUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val userName = itemView.findViewById<TextView>(R.id.tv_wait_user_title)
    val userGroup = itemView.findViewById<TextView>(R.id.tv_wait_user_group)

    fun bind(customData: UserListData) {
        userName.text = customData.userName
        userGroup.text = customData.userGroup
        itemViewClickEvent()
    }

    //해당 함수 이벤트를 부른 곳에서 할당할 예정. Activity에서 돌리자.
    private fun itemViewClickEvent(){
        itemView.setOnClickListener {
            val gotoUserInfoIntent = Intent(itemView.context,UserInfoActivity::class.java)

            itemView.context
                .startActivity(gotoUserInfoIntent)
        }
    }
}