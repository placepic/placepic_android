package place.pic.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.databinding.ActivityMyPageSettingBinding
import place.pic.ui.dialog.SimpleDialog
import place.pic.ui.main.OnBoardingActivity

class MyPageSettingActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMyPageSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_my_page_setting)
        binding.myPageSettingActivity=this

        binding.tvSettingTopBarTitle.text = "설정"
        binding.imgTopBarBackBtn.setOnClickListener {
            finish()
        }

        binding.clBtnProfileEdit.setOnClickListener {
            val profileEditIntent = Intent(this, ProfileEditActivity::class.java)
            startActivity(profileEditIntent)
        }

        binding.clBtnGroupChange.setOnClickListener {
            val groupChangeIntent = Intent(this, GroupChangeActivity::class.java)
            startActivity(groupChangeIntent)
        }

        binding.clBtnAdminPage.setOnClickListener {
            val adminIntent = Intent(this, AdminActivity::class.java)
            startActivity(adminIntent)
        }

        binding.clBtnLogout.setOnClickListener {
            SimpleDialog(this).apply {
                setContent(R.string.logout_message)
                setCancelClickListener(R.string.cancel) { dismiss() }
                setOkClickListener(R.string.logout) {
                    dismiss()
                    PlacepicAuthRepository.getInstance(this@MyPageSettingActivity).removeUserToken()
                    PlacepicAuthRepository.getInstance(this@MyPageSettingActivity).removeGroupId()
                    val onBoardingIntent = Intent(context, OnBoardingActivity::class.java)
                    onBoardingIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    startActivity(onBoardingIntent)
                    finish()
                }
            }.show()
        }
    }
}