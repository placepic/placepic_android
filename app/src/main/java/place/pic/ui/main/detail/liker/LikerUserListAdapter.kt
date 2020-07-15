package place.pic.ui.main.detail.liker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.data.remote.response.Like

class LikerUserListAdapter(
    private val context: Context,
    private val datas: List<Like>
) : RecyclerView.Adapter<LikerUserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikerUserListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_like_user_list, parent, false)
        return LikerUserListViewHolder(view)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: LikerUserListViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}