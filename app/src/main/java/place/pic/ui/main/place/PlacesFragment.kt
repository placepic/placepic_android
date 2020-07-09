package place.pic.ui.main.place

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import place.pic.data.entity.Subway
import place.pic.databinding.FragmentPlacesBinding
import place.pic.ui.main.place.adapter.PlacesPagerAdapter
import place.pic.ui.main.place.bottomsheet.PlaceFeaturesFragment
import place.pic.ui.main.place.bottomsheet.PlaceKeywordsFragment
import place.pic.ui.search.subway.SubwaySearchActivity
import java.util.*

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlacesFragment : Fragment() {

    private val placesViewModel = PlacesViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlacesBinding.inflate(inflater, container, false)
        initView(binding)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SubwaySearchActivity.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                handleSelectedSubways(data)
            }
        }
    }

    private fun handleSelectedSubways(data: Intent?) {
        if (data == null) return
        val subways = data.getSerializableExtra(SUBWAYS_KEY) as List<Subway>
        placesViewModel.selectSubways(subways)
    }

    private fun initView(binding: FragmentPlacesBinding) {
        binding.viewModel = placesViewModel
        binding.lifecycleOwner = this
        binding.tabPlaceCategories.setupWithViewPager(binding.pagerFilterPlaces)
        binding.pagerFilterPlaces.adapter = PlacesPagerAdapter(childFragmentManager)
        binding.pagerFilterPlaces.offscreenPageLimit = 2

        binding.pagerFilterPlaces.addOnPageChangeListener(PlacesPageChangeListener(placesViewModel))
        binding.btnSelectSubways.setOnClickListener { deploySubwaySearchActivity() }
        binding.btnSelectKeywords.setOnClickListener { showSelectKeywordBottomSheet() }
        binding.btnSelectPlaceFeatures.setOnClickListener { showSelectPlaceFeaturesBottomSheet() }
    }

    private fun deploySubwaySearchActivity() {
        val intent = Intent(activity, SubwaySearchActivity::class.java)
        val currentSelectedSubways = placesViewModel.selectedSubways.value ?: emptyList()
        intent.putExtra(SUBWAYS_KEY, ArrayList(currentSelectedSubways))

        startActivityForResult(intent, SubwaySearchActivity.REQUEST_CODE)
    }

    private fun showSelectKeywordBottomSheet() {
        PlaceKeywordsFragment()
            .show(childFragmentManager, null)
    }

    private fun showSelectPlaceFeaturesBottomSheet() {
        PlaceFeaturesFragment()
            .show(childFragmentManager, null)
    }

    companion object {
        const val SUBWAYS_KEY = "subways"
    }
}