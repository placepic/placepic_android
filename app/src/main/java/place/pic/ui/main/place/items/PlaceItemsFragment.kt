package place.pic.ui.main.place.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.data.entity.Place
import place.pic.databinding.FragmentLoadingBinding
import place.pic.databinding.FragmentPlaceItemsBinding
import place.pic.ui.main.place.adapter.PlacesAdapter

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlaceItemsFragment(placeType: Place.Type) : Fragment() {

    private var placeItemsAdapter = PlacesAdapter()
    private val placeItemsViewModel = PlaceItemsViewModel(placeType)

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

        placeItemsViewModel.placeItems.observe(this, Observer {
            placeItemsAdapter.submitList(it)
        })
    }

    override fun onResume() {
        super.onResume()

        placeItemsViewModel.loadPlaceItems()
    }

    private fun inflateAsyncLayout(target: FragmentLoadingBinding, container: ViewGroup?) {
        AsyncLayoutInflater(context!!).inflate(
            R.layout.fragment_place_items,
            container
        ) { view, _, _ ->
            val binding = FragmentPlaceItemsBinding.bind(view)
            target.container.addView(binding.root)
            target.progressBar.visibility = View.GONE
            binding.viewModel = placeItemsViewModel
            binding.lifecycleOwner = this
            binding.rvPlaces.adapter = placeItemsAdapter
//            val rvPlaces = view.findViewById<RecyclerView>(R.id.rv_places)
//            rvPlaces.adapter = placeItemsAdapter
        }
    }

}