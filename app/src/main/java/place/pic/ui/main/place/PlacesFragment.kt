package place.pic.ui.main.place

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.databinding.FragmentPlacesBinding
import place.pic.ui.main.place.adapter.PlacesPagerAdapter
import place.pic.ui.main.place.bottomsheet.PlaceFeaturesFragment
import place.pic.ui.main.place.bottomsheet.PlaceFeaturesFragment.Companion.FEATURES_KEY
import place.pic.ui.main.place.bottomsheet.PlaceKeywordsFragment
import place.pic.ui.main.place.bottomsheet.PlaceKeywordsFragment.Companion.KEYWORDS_KEY
import place.pic.ui.main.place.items.PlaceItemsFragment
import place.pic.ui.search.subway.SubwaySearchActivity
import java.util.*

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlacesFragment : Fragment() {

    private val placesViewModel = PlacesViewModel()
    private lateinit var pagerAdapter: PlacesPagerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        pagerAdapter = PlacesPagerAdapter(childFragmentManager)
    }

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
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SubwaySearchActivity.REQUEST_CODE) {
                handleSelectedSubways(data ?: return)
            }
            if (requestCode == PlaceKeywordsFragment.REQUEST_CODE) {
                handleSelectedKeywords(data ?: return)
            }
            if (requestCode == PlaceFeaturesFragment.REQUEST_CODE) {
                handleSelectedFeatures(data ?: return)
            }
            updatePlaceItems(pagerAdapter.getItem(placesViewModel.currentPagerPosition) as PlaceItemsFragment)
        }
    }

    private fun handleSelectedSubways(data: Intent) {
        val subways = data.getSerializableExtra(SUBWAYS_KEY) as List<Subway>
        placesViewModel.selectSubways(subways)
    }

    private fun handleSelectedKeywords(data: Intent) {
        val keywords = data.getSerializableExtra(KEYWORDS_KEY) as List<KeywordTag>
        placesViewModel.selectKeywords(keywords)
    }

    private fun handleSelectedFeatures(data: Intent) {
        val features = data.getSerializableExtra(FEATURES_KEY) as List<UsefulTag>
        placesViewModel.selectFeatures(features)
    }

    private fun updatePlaceItems(fragment: PlaceItemsFragment) {
        fragment.updatePlaceItems(
            placesViewModel.selectedSubways.value,
            placesViewModel.selectedKeywords.value,
            placesViewModel.selectedFeatures.value
        )
    }

    private fun initView(binding: FragmentPlacesBinding) {
        binding.viewModel = placesViewModel
        binding.lifecycleOwner = this
        binding.tabPlaceCategories.setupWithViewPager(binding.pagerFilterPlaces)
        binding.pagerFilterPlaces.adapter = pagerAdapter
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
        val placeKeywords = placesViewModel.getCurrentPlaceTypeDetails().placeKeywords
        PlaceKeywordsFragment(placeKeywords)
            .apply {
                setTargetFragment(this@PlacesFragment, PlaceKeywordsFragment.REQUEST_CODE)
                setAlreadySelectedKeywords(placesViewModel.selectedKeywords.value)
            }
            .show(getSupportFragmentManager(), null)
    }

    private fun showSelectPlaceFeaturesBottomSheet() {
        val placeFeatures = placesViewModel.getCurrentPlaceTypeDetails().placeFeatures
        PlaceFeaturesFragment(placeFeatures)
            .apply {
                setTargetFragment(this@PlacesFragment, PlaceFeaturesFragment.REQUEST_CODE)
                setAlreadySelectedFeatures(placesViewModel.selectedFeatures.value)
            }
            .show(getSupportFragmentManager(), null)
    }

    private fun getSupportFragmentManager() = activity?.supportFragmentManager
        ?: throw IllegalStateException("activity cannot be null")

    companion object {
        const val SUBWAYS_KEY = "subways"
    }
}