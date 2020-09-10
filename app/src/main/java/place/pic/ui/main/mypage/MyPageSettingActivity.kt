package place.pic.ui.main.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_my_page_setting.*
import kotlinx.android.synthetic.main.fragment_my_page_setting.view.*
import place.pic.R
import place.pic.ui.main.MainActivity
import place.pic.ui.main.place.PlacesFragment
import place.pic.ui.signup.SignupSecondActivity

class MyPageSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_setting)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mypage_frame, MyPageSettingFragment())
            .commit()

    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mypage_frame, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}