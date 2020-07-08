package place.pic.ui.search.subway

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import place.pic.R
import place.pic.data.entity.Subway
import place.pic.databinding.ActivitySearchSubwayBinding
import place.pic.ui.main.place.PlacesFragment.Companion.SUBWAYS_KEY
import place.pic.ui.search.subway.adapter.SelectedSubwaysAdapter
import place.pic.ui.search.subway.adapter.SubwaysAdapter

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

/**
이미 선택된 역을 나타내려면 putExtra에 "subways" 키로 ArrayList<Subway> 를 넘겨주면 됩니다.
또한, 선택된 역들을 받아오려면 getExtra를 통해 "subways" 키로 List<Subway> 캐스팅해오면 됩니다.
 */

class SubwaySearchActivity : AppCompatActivity() {

    private val selectedSubwaysAdapter by lazy { SelectedSubwaysAdapter() }
    private val searchedSubwaysAdapter by lazy { SubwaysAdapter() }
    private val subwaySearchViewModel by lazy { SubwaySearchViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        getAlreadySelectedSubways()
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
        binding.btnSubmitSubways.setOnClickListener { finishSelectSubways() }
        binding.btnBack.setOnClickListener { finish() }
        setContentView(binding.root)
    }

    private fun initAdapters(binding: ActivitySearchSubwayBinding) {
        searchedSubwaysAdapter.setSubwayClickListener { onSearchedSubwayClick(binding, it) }
        selectedSubwaysAdapter.setSubwaySelectCancelListener {
            subwaySearchViewModel.removeSelectedSubways(it)
        }
    }

    private fun onSearchedSubwayClick(binding: ActivitySearchSubwayBinding, subway: Subway) {
                if (subwaySearchViewModel.getCurrentSelectedSubways().size >= MAX_PICK_SUBWAYS_COUNT) {
            Toast.makeText(this, R.string.subways_pick_count_three, Toast.LENGTH_SHORT).show()
            return
        }
        if (subwaySearchViewModel.contains(subway)) {
            Toast.makeText(this, R.string.already_selected_subway, Toast.LENGTH_SHORT).show()
        }
        subwaySearchViewModel.addSelectedSubways(subway)
        binding.rvSelectedSubways.scrollToPosition(0)
    }

    private fun getAlreadySelectedSubways() {
        val subways = intent.getSerializableExtra(SUBWAYS_KEY) ?: return
        subwaySearchViewModel.loadAlreadySelectedSubways(subways as List<Subway>)
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

    private fun finishSelectSubways() {
        val selectedSubways = ArrayList(subwaySearchViewModel.getCurrentSelectedSubways())
        val intent = Intent().apply { putExtra(SUBWAYS_KEY, selectedSubways) }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val REQUEST_CODE = 2000

        const val MAX_PICK_SUBWAYS_COUNT = 3
    }
}