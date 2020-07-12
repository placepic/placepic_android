package place.pic.ui.search.place

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

/**
 * Created By kimdahyee
 * on 7월 , 2020
 */


class PlaceSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val placeName = itemView.findViewById<TextView>(R.id.tv_place_name)
    val placeLocation = itemView.findViewById<TextView>(R.id.tv_place_location)

    fun bind(placeSearchData : PlaceSearchData) {
        placeName.text = placeSearchData.placeName
        placeLocation.text = placeSearchData.placeRoadAddress
    }
}