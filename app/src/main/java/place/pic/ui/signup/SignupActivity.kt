package place.pic.ui.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup.*
import place.pic.R
import place.pic.showToast
import place.pic.ui.login.LoginActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        img_sign_top_bar_back_btn.setOnClickListener {
            //로그인 화면으로 가버령
            finish()
        }
        btn_signnup.setOnClickListener {
            if (et_sign_email.text.isNullOrBlank() || et_sign_password.text.isNullOrBlank() || et_sign_ok_password.text.isNullOrBlank())
            {
                //빈칸이 있을 때
                showToast("빈칸이있어용")
            }
            else if(et_sign_password.getText().toString() != et_sign_ok_password.getText().toString()) {
                //비밀번호 다를 때
                showToast("비밀번호가 달라용")
            }
            else {
                val intent = Intent(this, SignupSecondActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }
    }
}

