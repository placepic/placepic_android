package place.pic.ui.main.mypage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import kotlinx.android.synthetic.main.activity_my_page_setting.view.*
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.fragment_admin.view.*
import kotlinx.android.synthetic.main.fragment_my_page.*
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

        val preferences = context?.getSharedPreferences("mypageuser", Context.MODE_PRIVATE)

        when (preferences?.getInt("mp_user_state", 1))
        {
            0 -> {
                (activity as MyPageSettingActivity).tv_no_admin?.text="관리자 웹페이지 준비중입니다."
                //img_no_admin.setImageResource(R.drawable.admin_ic_40px)
            }
            1 -> {
                (activity as MyPageSettingActivity).tv_no_admin?.text="관리중인 그룹이 없습니다."
            }
            else -> (activity as MyPageSettingActivity).tv_no_admin?.text="관리자 웹페이지 준비중입니다."
        }

       /* v.img_admin_top_bar_back_btn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        */

        return v
    }
}