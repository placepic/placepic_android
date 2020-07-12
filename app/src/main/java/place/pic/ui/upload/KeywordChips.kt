package place.pic.ui.upload

import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import place.pic.data.entity.KeywordTag
import place.pic.ui.tag.ChipFactory

/**
 * Created By Malibin
 * on 7월 13, 2020
 */

class KeywordChips(private val chipGroup: ChipGroup) {

    private lateinit var keywords: List<KeywordTag>
    private val selectedKeywords = mutableListOf<KeywordTag>()

    fun submitKeywords(keywords: List<KeywordTag>) {
        this.keywords = keywords
        selectedKeywords.clear()
        chipGroup.removeAllViews()
        this.keywords
            .map { convertToChip(it) }
            .forEach { chipGroup.addView(it) }
    }

    private fun convertToChip(keyword: KeywordTag): Chip {
        val layoutInflater = LayoutInflater.from(chipGroup.context)
        val chip = ChipFactory.newInstance(layoutInflater)
        chip.text = keyword.tagName
        chip.setOnClickListener { onKeywordClick(keyword) }
        return chip
    }

    private fun onKeywordClick(keyword: KeywordTag) {
        if (selectedKeywords.contains(keyword)) {
            selectedKeywords.remove(keyword)
            return
        }
        selectedKeywords.add(keyword)
    }
}
