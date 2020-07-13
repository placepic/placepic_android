package place.pic.ui.upload

import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import place.pic.data.entity.UsefulTag
import place.pic.ui.tag.ChipFactory

/**
 * Created By Malibin
 * on 7월 13, 2020
 */

class FeatureChips(private val chipGroup: ChipGroup) {

    private lateinit var features: List<UsefulTag>
    private val selectedFeatures = mutableListOf<UsefulTag>()

    fun submitFeatures(features: List<UsefulTag>) {
        this.features = features
        selectedFeatures.clear()
        chipGroup.removeAllViews()
        this.features
            .map { convertToChip(it) }
            .forEach { chipGroup.addView(it) }
    }

    private fun convertToChip(feature: UsefulTag): Chip {
        val layoutInflater = LayoutInflater.from(chipGroup.context)
        val chip = ChipFactory.createSmallChip(layoutInflater)
        chip.text = feature.tagName
        chip.setOnClickListener { onFeatureClick(feature) }
        return chip
    }

    private fun onFeatureClick(feature: UsefulTag) {
        if (selectedFeatures.contains(feature)) {
            selectedFeatures.remove(feature)
            return
        }
        selectedFeatures.add(feature)
    }
}
