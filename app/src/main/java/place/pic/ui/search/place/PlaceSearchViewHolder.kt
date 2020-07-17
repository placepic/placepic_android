package place.pic.ui.search.place

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.createBitmap
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

/**
 * Created By kimdahyee
 * on 7월 , 2020
 */


class PlaceSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val layout = itemView.findViewById<ConstraintLayout>(R.id.ConstraintLayout_already)
    private val placeName = itemView.findViewById<TextView>(R.id.tv_place_name)
    private val placeLocation = itemView.findViewById<TextView>(R.id.tv_place_location)
    private val alreadyInView = itemView.findViewById<View>(R.id.view_already)
    private val alreadyInText = itemView.findViewById<TextView>(R.id.tv_already)

    fun bind(placeSearchData : PlaceSearchData) {
        placeName.text = placeSearchData.placeName
        placeLocation.text = placeSearchData.placeLocation

        if (placeSearchData.alreadyIn) { // 이미 등록된 view
            alreadyInView.visibility = View.VISIBLE
            alreadyInText.visibility = View.VISIBLE

            placeName.setTextColor(Color.parseColor("#E6E6E6"))
            placeLocation.setTextColor(Color.parseColor("#E6E6E6"))

            layout.isEnabled = false
        }

        if (!placeSearchData.alreadyIn) {
            alreadyInView.visibility = View.GONE
            alreadyInText.visibility = View.GONE

            placeName.setTextColor(Color.parseColor("#363636"))
            placeLocation.setTextColor(Color.parseColor("#CDCDCD"))

            layout.isEnabled = true
        }
    }
}