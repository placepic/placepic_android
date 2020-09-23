package place.pic.ui.main.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import place.pic.R

class MyPageSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_setting)

        img_top_bar_back_btn.setOnClickListener {
            finish()
            fragmentManager?.popBackStack()
        }

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