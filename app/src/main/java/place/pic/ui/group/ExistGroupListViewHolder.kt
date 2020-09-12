package place.pic.ui.group

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.util.glideProgressLoadingView
import java.lang.RuntimeException

class ExistGroupListViewHolder(
    itemView: View,
    private val groupFragment: Fragment
) : RecyclerView.ViewHolder(itemView) {
    private var bindGroupListEvent: BindGroupListEvent? = null

    val img_group_profile = itemView.findViewById<ImageView>(R.id.img_group_profile)
    val tv_group_title = itemView.findViewById<TextView>(R.id.tv_group_title)
    val tv_group = itemView.findViewById<TextView>(R.id.tv_group_count)

    private fun checkImplementInterFace() {
        if (groupFragment !is BindGroupListEvent) {
            throw RuntimeException(itemView.context.toString() + "must implement BindGroupListEvent")
        }
        bindGroupListEvent = groupFragment
    }


    fun bind(customData: ResponseGroupList) {
        checkImplementInterFace()

        tv_group_title.text = customData.groupName
        tv_group.text = stringDataMapping(customData)
        Glide.with(itemView)
            .load(customData.groupImage)
            .placeholder(itemView.context.glideProgressLoadingView())
            .into(img_group_profile)
        itemViewClickEvent(customData)
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
        if (customData.state != 1) {
            itemView.setOnClickListener {
                bindGroupListEvent?.saveGroupIDListener(customData.groupIdx)
                bindGroupListEvent?.clickNonSignGroupListener()
            }
            return
        }
        itemView.setOnClickListener { bindGroupListEvent?.clickSignGroupListener() }
    }

}
