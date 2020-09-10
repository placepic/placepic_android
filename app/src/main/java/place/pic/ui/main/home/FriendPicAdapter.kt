package place.pic.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.ui.main.userlist.UserData

/**
 * Created By kimdahyee
 * on 09월 05일, 2020
 */

class FriendPicAdapter : RecyclerView.Adapter<FriendPicViewHolder>() {

    var datas: MutableList<FriendPicData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendPicViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_friend_pic, parent, false)
        val inflater = LayoutInflater.from(parent.context)
        return FriendPicViewHolder(view, inflater)
    }

    override fun onBindViewHolder(holder: FriendPicViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    /* infinite scroll 준비
    fun addItems (items: List<FriendPicData>) {
        clear()
        val size = itemCount + 2
        datas.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }*/

    private fun clear() {
        datas.clear()
    }
}