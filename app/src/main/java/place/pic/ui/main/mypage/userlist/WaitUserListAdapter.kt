package place.pic.ui.main.mypage.userlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

class WaitUserListAdapter (
    private val datas:MutableList<UserListData>,
    private val context: Context
) : RecyclerView.Adapter<WaitUserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitUserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_wait_user,parent,false)
        return WaitUserViewHolder(view)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: WaitUserViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}