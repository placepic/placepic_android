package place.pic.ui.main.place.bottomsheet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class PlaceKeywordsFragment(
    private val keywords: List<KeywordTag>
) : BottomSheetDialogFragment() {

    private val selectedKeywords = mutableListOf<KeywordTag>()

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

    private fun onKeywordClick(keyword: KeywordTag) {
        if (selectedKeywords.contains(keyword)) {
            selectedKeywords.remove(keyword)
            return
        }
        selectedKeywords.add(keyword)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        selectedKeywords.clear()
    }

    private fun initView(binding: BottomSheetPlaceChipsBinding) {
        binding.tvTitle.setText(R.string.keyword)
        binding.btnCancel.setOnClickListener { dismiss() }
        binding.btnSubmit.setOnClickListener { onSubmitClick() }
        insertKeywordChipViews(binding.chipGroup)
    }

    private fun onSubmitClick() {
        val intent = Intent()
        intent.putExtra(KEYWORDS_KEY, ArrayList(selectedKeywords))
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }

    private fun insertKeywordChipViews(chipGroup: ChipGroup) {
        for (keyword in keywords) {
            val chip = ChipFactory.newInstance(layoutInflater)
            chip.text = keyword.tagName
            chip.isChecked = isAlreadySelectedKeyword(keyword)
            chip.setOnClickListener { onKeywordClick(keyword) }
            chipGroup.addView(chip)
        }
    }

    private fun isAlreadySelectedKeyword(keyword: KeywordTag): Boolean {
        return selectedKeywords.contains(keyword)
    }

    fun setAlreadySelectedKeywords(keywords: List<KeywordTag>?) {
        selectedKeywords.addAll(keywords ?: emptyList())
    }

    companion object {
        const val REQUEST_CODE = 2002
        const val KEYWORDS_KEY = "keywords"
    }
}
