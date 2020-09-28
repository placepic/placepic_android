package place.pic.ui.main.home.friendpic

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.FileLoader
import place.pic.R

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

    /* infinite scroll 준비 */
    fun addItems (items: List<FriendPicData>) {
        clear()
        val size = itemCount - 1
        datas.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }

    private fun clear() {
        datas.clear()
    }
}