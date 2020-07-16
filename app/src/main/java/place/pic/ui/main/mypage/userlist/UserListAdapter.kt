package place.pic.ui.main.mypage.userlist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */

class UserListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var userCount: Int = 0
    var datas : MutableList<UserData> = mutableListOf<UserData>()?.apply {add(0, UserData.empty())}

    override fun getItemViewType(position: Int) = when (position) {
        0 -> R.layout.item_member
        else -> R.layout.item_user_list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_member -> {
                val view = layoutInflater.inflate(R.layout.item_member, parent, false)
                MemberViewHolder(view)
            }
            R.layout.item_user_list -> {
                val view = layoutInflater.inflate(R.layout.item_user_list, parent, false)
                UserViewHolder(view)
            }
            else -> throw IllegalArgumentException("ViewType [$viewType] is unexpected")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MemberViewHolder) {
            holder.bind(userCount)
        }

        if (holder is UserViewHolder) {
            holder.bind(datas[position])
        }
    }

    inner class MemberViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val userCount = itemView.findViewById<TextView>(R.id.user_ranking)

        fun bind(userCount: Int) {
            this.userCount.text = userCount.toString()
        }
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val rank = itemView.findViewById<TextView>(R.id.user_ranking)
        private val profileImageUrl = itemView.findViewById<ImageView>(R.id.user_image)
        private val userName = itemView.findViewById<TextView>(R.id.user_name)
        private val part = itemView.findViewById<TextView>(R.id.user_part)
        private val postCount = itemView.findViewById<TextView>(R.id.user_post_count)

        fun bind(userData : UserData) {
            rank.text = userData.rank.toString()
            Glide.with(itemView).load(userData.profileImageUrl).into(profileImageUrl)
            userName.text = userData.userName
            part.text = userData.part
            postCount.text = userData.postCount.toString()

            rank.setTextColor(Color.parseColor("#636363"))

            if (rank.text == "-1") {
                rank.text = "-"
            }

            if (rank.text == "1" || rank.text == "2" || rank.text == "3") {
                rank.setTextColor(Color.parseColor("#F65C6C"))
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun addItems (items: List<UserData>) {
        clear()
        val size = itemCount + 2
        datas.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }

    private fun clear() {
        datas.clear()
    }
}