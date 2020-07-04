package place.pic.ui.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup_second.*
import place.pic.R
import place.pic.showToast
import place.pic.ui.login.LoginActivity

class SignupSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_second)

        img_sign_top_bar_back_btn2.setOnClickListener {

            finish()
            overridePendingTransition(0, 0)
            //이전 화면으로 이동
        }
        btn_signnup_second.setOnClickListener {
            if (et_sign_name.text.isNullOrBlank() || et_sign_birth.text.isNullOrBlank()) //sex도 추가 해야돼!!!!
            {
                //빈칸이 있을 때
                showToast("빈칸이있어용")
            }
            else {
                //setResult(Activity.RESULT_OK, intent)
                //finish()
            }
        }
    }
}

