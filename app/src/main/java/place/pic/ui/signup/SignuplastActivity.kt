package place.pic.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup_second.*
import kotlinx.android.synthetic.main.activity_signuplast.*
import place.pic.R
import place.pic.ui.login.LoginActivity

class SignuplastActivity : AppCompatActivity() {

    override fun onBackPressed() {
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signuplast)

        btn_signnup_last.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)

            finish()
        }
    }
}