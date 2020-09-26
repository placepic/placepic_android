package place.pic.ui.main.mypage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import kotlinx.android.synthetic.main.fragment_my_page_setting.view.*
import place.pic.R
import place.pic.ui.dialog.SimpleDialog
import place.pic.ui.login.LoginActivity


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
            //activity?.finish()
        }
        v.cl_btn_admin_page.setOnClickListener {
            (activity as MyPageSettingActivity).replaceFragment(AdminFragment.newInstance(),"AdminFragment")
        }
        v.cl_btn_group_change.setOnClickListener {
            val intent3 = Intent(context, GroupChangeActivity::class.java)
            startActivity(intent3)
        }

        v.cl_btn_logout.setOnClickListener{
            SimpleDialog(activity as MyPageSettingActivity).apply {
                setContent(R.string.logout_message)
                setCancelClickListener(R.string.cancel) { dismiss() }
                setOkClickListener(R.string.logout) {
                    dismiss()
                    val preferences = (activity as MyPageSettingActivity).getSharedPreferences("temp", Context.MODE_PRIVATE)
                    val editor : SharedPreferences.Editor= preferences!!.edit()
                    val intent2 = Intent(context, LoginActivity::class.java)
                    editor.clear()
                    editor.apply()
                    startActivity(intent2)
                    activity?.finish()
                }
            }.show()
        }

        return v
    }


}

