package place.pic.ui.main.mypage.userlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */

class UserListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data : MutableList<UserData> = mutableListOf<UserData>()?.apply {add(0, UserData.empty())}

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
            //holder.bind()
        }

        if (holder is UserViewHolder) {
            holder.bind(data[position])
        }
    }

    inner class MemberViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val userCount = itemView.findViewById<TextView>(R.id.user_count)

        fun bind(memberData: MemberData) {
            userCount.text = memberData.userCount.toString()
        }
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
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

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItems (items: List<UserData>) {
        val size = itemCount + 2
        data.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }
}