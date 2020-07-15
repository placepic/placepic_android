package place.pic.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_login_page.*
import place.pic.R
import place.pic.ui.signup.SignupActivity

class LoginPageActivity : AppCompatActivity(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        init()
        setStatusBar()
    }

    fun setStatusBar(colors: Int = R.color.colorPrimary) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (colors != android.R.color.transparent) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (colors == R.color.colorPrimary)
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = resources.getColor(colors, null)
            } else
                window.statusBarColor = ContextCompat.getColor(this, colors)
        }
    }
    private fun init(){
        buttonMapping()
    }

    private fun buttonMapping(){
        btn_goto_login.setOnClickListener(this)
        btn_goto_signup.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_goto_login -> {
                val gotoLoginIntent = Intent(this,LoginActivity::class.java)
                gotoLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(gotoLoginIntent)
                finish()
            }
            R.id.btn_goto_signup->
            {
                val gotoSignupIntent = Intent(this,SignupActivity::class.java)
                gotoSignupIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(gotoSignupIntent)
            }
        }
    }
}