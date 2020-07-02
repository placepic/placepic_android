package place.pic.ui.search.subway

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import place.pic.databinding.ActivitySearchSubwayBinding

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

class SubwaySearchActivity : AppCompatActivity() {

    private val selectedSubwaysAdapter by lazy { SelectedSubwaysAdapter() }
    private val searchedSubwaysAdapter by lazy { SubwaysAdapter() }
    private val subwaySearchViewModel by lazy { SubwaySearchViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        subscribeSelectedSubways()
        subscribeSearchedSubways()
        subscribeSearchQuery()
    }

    private fun initView() {
        val binding = ActivitySearchSubwayBinding.inflate(layoutInflater)
        initAdapters(binding)
        binding.viewModel = subwaySearchViewModel
        binding.lifecycleOwner = this
        binding.rvSelectedSubways.adapter = selectedSubwaysAdapter
        binding.rvSearchedSubways.adapter = searchedSubwaysAdapter
        setContentView(binding.root)
    }

    private fun initAdapters(binding: ActivitySearchSubwayBinding) {
        searchedSubwaysAdapter.setSubwayClickListener {
            subwaySearchViewModel.addSelectedSubways(it)
            binding.rvSelectedSubways.scrollToPosition(0)
        }
        selectedSubwaysAdapter.setSubwaySelectCancelListener {
            subwaySearchViewModel.removeSelectedSubways(it)
        }
    }

    private fun subscribeSelectedSubways() {
        subwaySearchViewModel.selectedSubways.observe(this, Observer {
            selectedSubwaysAdapter.submitList(it)
        })
    }

    private fun subscribeSearchedSubways() {
        subwaySearchViewModel.searchedSubways.observe(this, Observer {
            searchedSubwaysAdapter.submitList(it)
        })
    }

    private fun subscribeSearchQuery() {
        subwaySearchViewModel.searchQuery.observe(this, Observer {
            subwaySearchViewModel.filterByName(it)
        })
    }
}