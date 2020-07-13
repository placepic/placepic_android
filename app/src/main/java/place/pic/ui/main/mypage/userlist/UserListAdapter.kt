package place.pic.ui.main.mypage.userlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */
 
class UserListAdapter (private val context : Context) : RecyclerView.Adapter<UserListViewHolder>() {

    var datas : MutableList<UserListData> = mutableListOf<UserListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}