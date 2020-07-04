package place.pic.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login_page.*
import place.pic.R

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
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_goto_login -> {
                val gotoLoginIntent = Intent(this,LoginActivity::class.java)
                startActivity(gotoLoginIntent)
            }
        }
    }
}