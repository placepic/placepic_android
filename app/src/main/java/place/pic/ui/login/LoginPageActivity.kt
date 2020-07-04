package place.pic.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login_page.*
import place.pic.R
import place.pic.ui.signup.SignupActivity

class LoginPageActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        init()
    }

    private fun init(){
        buttonMapping()
    }

    private fun buttonMapping(){
        btn_goto_login.setOnClickListener(this)
        //수정이가 추가했음!
        btn_goto_signup.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_goto_login -> {
                val gotoLoginIntent = Intent(this,LoginActivity::class.java)
                startActivity(gotoLoginIntent)
            }
            R.id.btn_goto_signup->
            {
                //수정이가 추가했음
                val gotoSignupIntent = Intent(this,SignupActivity::class.java)
                startActivity(gotoSignupIntent)
            }
        }
    }
}