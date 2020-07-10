package place.pic.ui.main.place.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import place.pic.R
import place.pic.data.entity.UsefulTag
import place.pic.databinding.BottomSheetPlaceChipsBinding
import place.pic.ui.tag.ChipFactory

/**
 * Created By Malibin
 * on 7월 06, 2020
 */

class PlaceFeaturesFragment(private val features: List<UsefulTag>) : BottomSheetDialogFragment() {

    private val featureChipViews = mutableListOf<Chip>()

    override fun getTheme() = R.style.Widget_AppTheme_BottomSheet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BottomSheetPlaceChipsBinding.inflate(layoutInflater, container, false)
        initView(binding)
        return binding.root
    }

    private fun initView(binding: BottomSheetPlaceChipsBinding) {
        binding.tvTitle.setText(R.string.place_feature)
        binding.btnSubmit.setOnClickListener { onSubmitClick() }
        binding.btnCancel.setOnClickListener { dismiss() }
        insertFeatureChipViews(binding.chipGroup)
    }

    private fun onSubmitClick() {

    }


    private fun insertFeatureChipViews(chipGroup: ChipGroup) {
        for (feature in features) {
            val chip = ChipFactory.newInstance(layoutInflater)
            chip.text = feature.tagName

            chipGroup.addView(chip)
            featureChipViews.add(chip)
        }
    }
}