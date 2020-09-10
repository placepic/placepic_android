package place.pic.ui.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import kotlinx.android.synthetic.main.activity_my_page_setting.view.*
import kotlinx.android.synthetic.main.fragment_admin.view.*
import place.pic.R


class AdminFragment : Fragment(){
    companion object {
        fun newInstance(): AdminFragment {
            return AdminFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_admin, container, false)
        (activity as MyPageSettingActivity).tv_setting_top_bar_title.text="관리자"
       /* v.img_admin_top_bar_back_btn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        */
        return v
    }
}