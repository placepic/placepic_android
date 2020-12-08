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

class GroupListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var datas: List<ResponseGroupList> = listOf()
    private var saveGroupIDListener: ((id: Int) -> Unit)? = null
    private var clickSignGroupListener: (() -> Unit)? = null
    private var clickNonSignGroupListener: ((groupCode: String) -> Unit)? = null
    private var clickApplyNewGroupListener: (() -> Unit)? = null

    fun setSaveGroupIDListener(listener: (id: Int) -> Unit) {
        this.saveGroupIDListener = listener
    }

    fun setClickSignGroupListener(listener: () -> Unit) {
        this.clickSignGroupListener = listener
    }

    fun setClickNonSignGroupListener(listener: (groupCode: String) -> Unit) {
        this.clickNonSignGroupListener = listener
    }

    fun setClickApplyNewGroupListener(listener: () -> Unit) {
        this.clickApplyNewGroupListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return if (datas[position].groupIdx > 0) GROUP_LIST else APPLY_NEW_GROUP
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return if (viewType == GROUP_LIST) {
            ViewHolder(view.inflate(R.layout.item_group_list, parent, false))
        } else {
            ApplyGroupViewHolder(view.inflate(R.layout.item_apply_new_group_view, parent, false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GroupListAdapter.ViewHolder) {
            holder.bind(datas[position])
            return
        }
        (holder as GroupListAdapter.ApplyGroupViewHolder).bind()
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val img_group_profile: ImageView = itemView.findViewById(R.id.img_group_profile)
        private val tv_group_title: TextView = itemView.findViewById(R.id.tv_group_title)
        private val tv_group: TextView = itemView.findViewById(R.id.tv_group_count)

        fun bind(customData: ResponseGroupList) {
            tv_group_title.text = customData.groupName
            tv_group.text = stringDataMapping(customData)
            loadImage(customData.groupImage)
            itemView.setOnClickListener { itemViewClickEvent(customData) }
        }

        private fun loadImage(img_url: String) {
            Glide.with(itemView)
                .load(img_url)
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

    inner class ApplyGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.setOnClickListener{clickApplyNewGroupListener?.invoke()}
        }
    }

    companion object {
        private const val APPLY_NEW_GROUP = 0
        private const val GROUP_LIST = 1
    }

}