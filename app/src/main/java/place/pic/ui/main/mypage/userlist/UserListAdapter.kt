package place.pic.ui.main.mypage.userlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.ui.search.place.PlaceSearchData

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */
 
class UserListAdapter: RecyclerView.Adapter<UserListViewHolder>() {

    var datas : MutableList<UserListData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_user_list, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    fun addItems (items: List<UserListData>) {
        val size = itemCount
        datas.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }
}