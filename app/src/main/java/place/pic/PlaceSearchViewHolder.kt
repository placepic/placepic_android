package place.pic

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val placeName = itemView.findViewById<TextView>(R.id.tv_place_name)
    val placeLocation = itemView.findViewById<TextView>(R.id.tv_place_location)

    fun bind(placeSearchData : PlaceSearchData) {
        placeName.text = placeSearchData.placeName
        placeLocation.text = placeSearchData.placeLocation
    }
}