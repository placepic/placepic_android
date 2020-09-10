package place.pic.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import kotlinx.android.synthetic.main.fragment_profile_edit.view.*
import place.pic.R

class ProfileEditFragment : Fragment() {
    companion object {
        fun newInstance(): ProfileEditFragment {
            return ProfileEditFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as MyPageSettingActivity).tv_setting_top_bar_title.text="프로필 편집"
        val v = inflater.inflate(R.layout.fragment_profile_edit, container, false)
        v.img_profile_setting.setOnClickListener {
            



        }
        return v
    }
}