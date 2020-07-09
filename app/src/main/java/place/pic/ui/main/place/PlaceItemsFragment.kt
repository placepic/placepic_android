package place.pic.ui.main.place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.data.entity.Place
import place.pic.data.remote.request.PlacesRequest
import place.pic.data.tempGroupId
import place.pic.data.tempToken
import place.pic.databinding.FragmentLoadingBinding
import place.pic.ui.main.place.adapter.PlacesAdapter

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlaceItemsFragment(private val placeType: Place.Type) : Fragment() {

    private var placeItemsAdapter = PlacesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loadingFragment = FragmentLoadingBinding.inflate(inflater, container, false)
        inflateAsyncLayout(loadingFragment, container)
        return loadingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        PlacesRequest(
            groupIdx = tempGroupId,
            placeCategory = placeType.position.run { if (this == 0) null else this }
        ).apply {
            addOnSuccessListener { placeItemsAdapter.submitList(it.data.map { it.toPlace() }) }
            addOnFailureListener { Log.d("Malibin", it.toString()) }
        }.send(tempToken)
    }

    private fun inflateAsyncLayout(target: FragmentLoadingBinding, container: ViewGroup?) {
        AsyncLayoutInflater(context!!).inflate(
            R.layout.fragment_place_items,
            container
        ) { view, _, _ ->
            target.container.addView(view)
            target.progressBar.visibility = View.GONE
            val rvPlaces = view.findViewById<RecyclerView>(R.id.rv_places)
            rvPlaces.adapter = placeItemsAdapter
        }
    }

}