package place.pic.ui.signup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup_second.*
import kotlinx.android.synthetic.main.activity_signuplast.*
import place.pic.R
import place.pic.ui.login.LoginActivity
import place.pic.ui.login.LoginPageActivity

class SignuplastActivity : AppCompatActivity() {

    override fun onBackPressed() {
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signuplast)

        btn_signnup_last.setOnClickListener {
            val preferences = this.getSharedPreferences("temp", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor= preferences!!.edit()

            val intent = Intent(this, LoginActivity::class.java)

            editor.clear()
            editor.apply()
            
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)

            finish()
        }
    }
}