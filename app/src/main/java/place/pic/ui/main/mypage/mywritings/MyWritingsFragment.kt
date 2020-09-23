package place.pic.ui.main.mypage.mywritings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.PlaceGridItem
import place.pic.databinding.FragmentMyWritingsBinding
import place.pic.ui.main.detail.DetailViewActivity
import place.pic.ui.main.place.adapter.PlaceGridItemsAdapter

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

class MyWritingsFragment : Fragment() {

    private lateinit var placeGridItemsAdapter: PlaceGridItemsAdapter
    private lateinit var myWritingsViewModel: MyWritingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyWritingsBinding.inflate(inflater, container, false)
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentMyWritingsBinding) {
        myWritingsViewModel =
            MyWritingsViewModel(PlacepicAuthRepository.getInstance(requireContext()))
        placeGridItemsAdapter = PlaceGridItemsAdapter()
        placeGridItemsAdapter.setItemClickListener { onMyWritingClick(it) }
        binding.rvMyWritings.adapter = placeGridItemsAdapter
        binding.lifecycleOwner = this
        binding.viewModel = myWritingsViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeMyWritings()
        myWritingsViewModel.requestMyWritings()
    }

    private fun subscribeMyWritings() {
        myWritingsViewModel.myWritings.observe(viewLifecycleOwner) {
            placeGridItemsAdapter.submitList(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == PLACE_DELETED) {
            val placeIdx = data?.getIntExtra("placeIdx", -1) ?: return
            myWritingsViewModel.removeMyWriting(placeIdx)
        }
    }

    private fun onMyWritingClick(placeGridItem: PlaceGridItem) {
        val intent = Intent(requireActivity(), DetailViewActivity::class.java)
        intent.putExtra("placeIdx", placeGridItem.placeIdx)
        startActivityForResult(intent, 5000)
    }

    companion object {
        const val PLACE_DELETED = 1
    }
}