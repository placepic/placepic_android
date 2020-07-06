package place.pic.ui.main.place.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import place.pic.R
import place.pic.databinding.BottomSheetPlaceChipsBinding

/**
 * Created By Malibin
 * on 7월 06, 2020
 */

class PlaceKeywordsFragment : BottomSheetDialogFragment() {

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
    }

    private fun onSubmitClick() {

    }

}