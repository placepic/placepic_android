package place.pic.ui.main.place.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import place.pic.R
import place.pic.data.entity.KeywordTag
import place.pic.databinding.BottomSheetPlaceChipsBinding
import place.pic.ui.tag.ChipFactory

/**
 * Created By Malibin
 * on 7월 06, 2020
 */

class PlaceKeywordsFragment(private val keywords: List<KeywordTag>) : BottomSheetDialogFragment() {

    private val keywordChipViews = mutableListOf<Chip>()

    override fun getTheme() = R.style.Widget_AppTheme_BottomSheet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BottomSheetPlaceChipsBinding.inflate(inflater, container, false)
        initView(binding)
        return binding.root
    }

    private fun initView(binding: BottomSheetPlaceChipsBinding) {
        binding.tvTitle.setText(R.string.keyword)
        binding.btnCancel.setOnClickListener { dismiss() }
        binding.btnSubmit.setOnClickListener { onSubmitClick() }
        insertKeywordChipViews(binding.chipGroup)
    }

    private fun onSubmitClick() {

    }

    private fun insertKeywordChipViews(chipGroup: ChipGroup) {
        for (keyword in keywords) {
            val chip = ChipFactory.newInstance(layoutInflater)
            chip.text = keyword.tagName

            chipGroup.addView(chip)
            keywordChipViews.add(chip)
        }
    }

}