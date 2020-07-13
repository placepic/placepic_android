package place.pic.ui.group

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.response.ResponseGroupList

class ExistGroupListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val img_group_profile = itemView.findViewById<ImageView>(R.id.img_group_profile)
    val tv_group_title = itemView.findViewById<TextView>(R.id.tv_group_title)
    val tv_group = itemView.findViewById<TextView>(R.id.tv_group_count)

    private var detailGroupClickListener: (()->Unit)? = null

    private var placepicAuthRepository:PlacepicAuthRepository? = null

    fun bind(customData: ResponseGroupList) {
        tv_group_title.text = customData.groupName
        tv_group.text = stringDataMapping(customData)
        Glide.with(itemView).load(customData.groupImage).into(img_group_profile)
        itemViewClickEvent(customData)
    }
    
    private fun stringDataMapping(customData: ResponseGroupList):String{
        return itemView.context
            .getString(
                    R.string.group_count,
                    customData.userCount,
                    customData.postCount
                )
    }

    private fun itemViewClickEvent(customData: ResponseGroupList){
        itemView.setOnClickListener {
            placepicAuthRepository?.saveGroupId(customData.groupIdx)
            detailGroupClickListener?.invoke()
        }
    }

    fun setDetailGroupClickListener(listener: (() -> Unit)?) {
        this.detailGroupClickListener = listener
    }

    fun setPlacepicAuthRepository(repository: PlacepicAuthRepository){
        this.placepicAuthRepository = repository
    }
}
