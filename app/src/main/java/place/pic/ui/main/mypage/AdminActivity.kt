package place.pic.ui.main.mypage

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_admin.tv_no_admin
import place.pic.R
import place.pic.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity()  {
    private lateinit var binding:ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_admin)
        binding.adminActivity=this

        val preferences = this.getSharedPreferences("mypageuser", Context.MODE_PRIVATE)
        when (preferences?.getInt("mp_user_state", 1))
        {
            0 -> {
                binding.tvNoAdmin.text="관리자 웹페이지 준비중입니다."
                //img_no_admin.setImageResource(R.drawable.admin_ic_40px)
            }
            1 -> {
                binding.tvNoAdmin.text="관리중인 그룹이 없습니다."
            }
            else -> binding.tvNoAdmin.text="관리자 웹페이지 준비중입니다."
        }

        binding.imgTopBarBackBtn.setOnClickListener {
            finish()
        }
    }
}