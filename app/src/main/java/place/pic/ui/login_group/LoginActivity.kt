package place.pic.ui.login_group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import place.pic.R
import place.pic.ui.login_group.group.GroupActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        setButtonEventListener()
    }

    private fun setButtonEventListener() {
        btn_home_signin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_home_signin -> {
                val goToGroupIntent = Intent(this, GroupActivity::class.java)
                startActivity(goToGroupIntent)
                finish()
            }
        }
    }
}