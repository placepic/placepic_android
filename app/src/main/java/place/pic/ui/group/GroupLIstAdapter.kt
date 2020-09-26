package place.pic.ui.group

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.util.glideProgressLoadingView

class GroupListAdapter : RecyclerView.Adapter<GroupListAdapter.ViewHolder>() {

    var datas: List<ResponseGroupList> = listOf()
    private var saveGroupIDListener: ((id: Int) -> Unit)? = null
    private var clickSignGroupListener: (() -> Unit)? = null
    private var clickNonSignGroupListener: ((groupCode:String) -> Unit)? = null

    fun setSaveGroupIDListener(listener: (id: Int) -> Unit) {
        this.saveGroupIDListener = listener
    }

    fun setClickSignGroupListener(listener: () -> Unit) {
        this.clickSignGroupListener = listener
    }

    fun setClickNonSignGroupListener(listener: (groupCode:String) -> Unit) {
        this.clickNonSignGroupListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val itemView = view.inflate(R.layout.item_group_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupListAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_group_profile = itemView.findViewById<ImageView>(R.id.img_group_profile)
        val tv_group_title = itemView.findViewById<TextView>(R.id.tv_group_title)
        val tv_group = itemView.findViewById<TextView>(R.id.tv_group_count)

        fun bind(customData: ResponseGroupList) {
            tv_group_title.text = customData.groupName
            tv_group.text = stringDataMapping(customData)
            loadImage(customData.groupImage)
            itemView.setOnClickListener { itemViewClickEvent(customData) }
        }

        private fun loadImage(img_url: String) {
            Glide.with(itemView)
                .load(img_url)
                .placeholder(itemView.context.glideProgressLoadingView())
                .into(img_group_profile)
        }

        private fun stringDataMapping(customData: ResponseGroupList): String {
            return itemView.context
                .getString(
                    R.string.group_count,
                    customData.userCount,
                    customData.postCount
                )
        }

        private fun itemViewClickEvent(customData: ResponseGroupList) {
            saveGroupIDListener?.invoke(customData.groupIdx)
            if (customData.state == 1) {
                clickSignGroupListener?.invoke()
                return
            }
            clickNonSignGroupListener?.invoke(customData.groupCode)
        }
    }

}