package place.pic.ui.main.mypage.mywritings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.MyWriting
import place.pic.databinding.FragmentMyWritingsBinding
import place.pic.ui.main.detail.DetailViewActivity

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

class MyWritingsFragment : Fragment() {

    private lateinit var myWritingsAdapter: MyWritingsAdapter
    private lateinit var myWritingsViewModel: MyWritingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyWritingsBinding.inflate(inflater)
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentMyWritingsBinding) {
        myWritingsViewModel =
            MyWritingsViewModel(PlacepicAuthRepository.getInstance(requireContext()))
        myWritingsAdapter = MyWritingsAdapter()
        myWritingsAdapter.setItemClickListener { onMyWritingClick(it) }
        binding.rvMyWritings.adapter = myWritingsAdapter
        binding.lifecycleOwner = this
        binding.viewModel = myWritingsViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeMyWritings()
        myWritingsViewModel.requestMyWritings()
    }

    private fun subscribeMyWritings() {
        myWritingsViewModel.myWritings.observe(this, Observer {
            myWritingsAdapter.submitList(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == PLACE_DELETED) {
            val placeIdx = data?.getLongExtra("placeIdx", -1) ?: return
            myWritingsViewModel.removeMyWriting(placeIdx.toInt())
        }
    }

    private fun onMyWritingClick(myWriting: MyWriting) {
        val intent = Intent(requireActivity(), DetailViewActivity::class.java)
        intent.putExtra("placeIdx", myWriting.placeIdx)
        startActivityForResult(intent, 5000)
    }

    companion object {
        const val PLACE_DELETED = 1
    }
}