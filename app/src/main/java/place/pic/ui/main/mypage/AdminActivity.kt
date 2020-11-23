package place.pic.ui.main.mypage

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_admin.tv_no_admin
import kotlinx.android.synthetic.main.fragment_admin.*
import place.pic.R

class AdminActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val preferences = this.getSharedPreferences("mypageuser", Context.MODE_PRIVATE)

        when (preferences?.getInt("mp_user_state", 1))
        {
            0 -> {
                this.tv_no_admin?.text="관리자 웹페이지 준비중입니다."
                //img_no_admin.setImageResource(R.drawable.admin_ic_40px)
            }
            1 -> {
                this.tv_no_admin?.text="관리중인 그룹이 없습니다."
            }
            else -> this.tv_no_admin?.text="관리자 웹페이지 준비중입니다."
        }

        this.img_top_bar_back_btn.setOnClickListener {
            finish()
        }
    }
}