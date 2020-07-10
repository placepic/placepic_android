package place.pic.ui.main.place.bottomsheet

import android.app.Activity
import android.content.Intent
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

class PlaceFeaturesFragment(
    private val features: List<UsefulTag>
) : BottomSheetDialogFragment() {

    private val selectedFeatures = mutableListOf<UsefulTag>()

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

    private fun onFeatureClick(feature: UsefulTag) {
        if (selectedFeatures.contains(feature)) {
            selectedFeatures.remove(feature)
            return
        }
        selectedFeatures.add(feature)
    }

    private fun initView(binding: BottomSheetPlaceChipsBinding) {
        binding.tvTitle.setText(R.string.place_feature)
        binding.btnSubmit.setOnClickListener { onSubmitClick() }
        binding.btnCancel.setOnClickListener { dismiss() }
        insertFeatureChipViews(binding.chipGroup)
    }

    private fun onSubmitClick() {
        val intent = Intent()
        intent.putExtra(FEATURES_KEY, ArrayList(selectedFeatures))
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }

    private fun insertFeatureChipViews(chipGroup: ChipGroup) {
        for (feature in features) {
            val chip = ChipFactory.newInstance(layoutInflater)
            chip.text = feature.tagName
            chip.setOnClickListener { onFeatureClick(feature) }
            chipGroup.addView(chip)
        }
    }

    companion object {
        const val REQUEST_CODE = 2001
        const val FEATURES_KEY = "features"
    }
}