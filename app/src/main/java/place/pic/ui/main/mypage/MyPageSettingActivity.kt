package place.pic.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import kotlinx.android.synthetic.main.fragment_my_page_setting.view.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.ui.dialog.SimpleDialog
import place.pic.ui.main.OnBoardingActivity

class MyPageSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_setting)

        this.tv_setting_top_bar_title.text = "설정"

        this.img_top_bar_back_btn.setOnClickListener {
            finish()
        }

        cl_btn_profile_edit.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }

        cl_btn_group_change.setOnClickListener {
            val intent3 = Intent(this, GroupChangeActivity::class.java)
            startActivity(intent3)
        }

        cl_btn_admin_page.setOnClickListener {
            val intent2 = Intent(this, AdminActivity::class.java)
            startActivity(intent2)
        }

        cl_btn_logout.setOnClickListener {
            SimpleDialog(this).apply {
                setContent(R.string.logout_message)
                setCancelClickListener(R.string.cancel) { dismiss() }
                setOkClickListener(R.string.logout) {
                    dismiss()
                    PlacepicAuthRepository.getInstance(this@MyPageSettingActivity).removeUserToken()
                    PlacepicAuthRepository.getInstance(this@MyPageSettingActivity).removeGroupId()
                    val intent2 = Intent(context, OnBoardingActivity::class.java)
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    startActivity(intent2)
                    finish()
                }
            }.show()
        }
    }
}