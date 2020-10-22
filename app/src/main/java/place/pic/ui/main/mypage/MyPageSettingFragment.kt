package place.pic.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import kotlinx.android.synthetic.main.fragment_my_page_setting.view.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.tempToken
import place.pic.ui.dialog.SimpleDialog
import place.pic.ui.main.MainActivity
import place.pic.ui.main.OnBoardingActivity


class MyPageSettingFragment : Fragment() {
    companion object {
        fun newInstance(): MyPageSettingFragment {
            return MyPageSettingFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val v = inflater.inflate(R.layout.fragment_my_page_setting, container, false)
        (activity as MyPageSettingActivity).tv_setting_top_bar_title.text="설정"
        v.cl_btn_profile_edit.setOnClickListener {
            val intent = Intent(context, ProfileEditActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        v.cl_btn_admin_page.setOnClickListener {
            (activity as MyPageSettingActivity).replaceFragment(
                AdminFragment.newInstance(),
                "AdminFragment"
            )
        }
        v.cl_btn_group_change.setOnClickListener {
            val intent3 = Intent(context, GroupChangeActivity::class.java)
            startActivity(intent3)
            activity?.finish()
        }

        v.cl_btn_logout.setOnClickListener{
            SimpleDialog(activity as MyPageSettingActivity).apply {
                setContent(R.string.logout_message)
                setCancelClickListener(R.string.cancel) { dismiss() }
                setOkClickListener(R.string.logout) {
                    dismiss()
                    PlacepicAuthRepository.getInstance(requireContext()).removeUserToken()
                    PlacepicAuthRepository.getInstance(requireContext()).removeGroupId()
                    val intent2 = Intent(context, OnBoardingActivity::class.java)
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    startActivity(intent2)
                    activity?.finishAffinity()
                }
            }.show()
        }
        return v
    }
}

