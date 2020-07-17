package place.pic.ui.main.place.items

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Place
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.databinding.FragmentLoadingBinding
import place.pic.databinding.FragmentPlaceItemsBinding
import place.pic.ui.main.bookmark.BookmarksFragment.Companion.PLACE_DELETED
import place.pic.ui.main.detail.DetailViewActivity
import place.pic.ui.main.place.adapter.PlacesAdapter

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlaceItemsFragment(private val placeType: Place.Type) : Fragment() {

    private lateinit var placeItemsViewModel: PlaceItemsViewModel
    private lateinit var placeItemsAdapter: PlacesAdapter

    private var isUpdatedFromFilter = false

    override fun onAttach(context: Context) {
        super.onAttach(context)

        placeItemsViewModel =
            PlaceItemsViewModel(placeType, PlacepicAuthRepository.getInstance(context))
        placeItemsAdapter = PlacesAdapter(placeItemsViewModel)
        placeItemsAdapter.setPlaceClickListener { onPlaceClick(it) }
    }

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

        if (isUpdatedFromFilter) {
            isUpdatedFromFilter = false
            return
        } //쉬바
        placeItemsViewModel.loadPlaceItems()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 6000) {
            if (resultCode == PLACE_DELETED) {
                val deletedPlaceId = data?.getLongExtra("placeIdx", -1) ?: return
                placeItemsViewModel.removePlace(deletedPlaceId)
            }
        }
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
        }
    }

    private fun onPlaceClick(place: Place) {
        val intent = Intent(requireActivity(), DetailViewActivity::class.java)
        intent.putExtra("placeIdx", place.id)
        startActivityForResult(intent, 6000)
    }

    fun updatePlaceItems(
        subways: List<Subway>?,
        keywords: List<KeywordTag>?,
        features: List<UsefulTag>?
    ) {
        isUpdatedFromFilter = true
        placeItemsViewModel.loadPlaceItems(
            subways = subways,
            keywordTags = keywords,
            usefulTags = features
        )
    }
}

// 이놈이 PlacesViewModel을 들고있는게나은거같다.. 더이상의 시간 지체는 내 욕심일 뿐으로
// updatePlaceItems 이후 바로 onResume이 호출되어 통신 두번하는 현상은 대충 onResume에 이상한 코드와
// 이 Fragment에 이상한 상태값을 추가하는걸로 응급처치했다... 에휴 나레기