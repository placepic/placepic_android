package place.pic.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search_subway.*
import kotlinx.android.synthetic.main.activity_sign_up_group.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.RequestSigninGroup
import place.pic.data.remote.request.MyPageRequest
import place.pic.data.remote.request.RequestLogin
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.LoginResponse
import place.pic.data.remote.response.MyPageResponse
import place.pic.showToast
import place.pic.ui.group.GroupListActivity
import place.pic.ui.main.place.PlacesFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), WriteFragment.BottomSheetListener {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tb: BottomNavigationView = findViewById(R.id.bottom_nav)
        tb.visibility = View.VISIBLE

        //처음 시작 화면 고정
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, PlacesFragment())
            .commit()

        //바텀 네비게이션 구현
        bottom_nav.setOnNavigationItemSelectedListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            when (it.itemId) {
                R.id.action_menu -> {
                    val fragmentA = PlacesFragment()
                    transaction.replace(R.id.main_frame, fragmentA, "name")
                }
                R.id.action_people -> {
                    val fragmentB = UserListFragment()
                    transaction.replace(R.id.main_frame, fragmentB, "people")
                }
                R.id.action_scrap -> {
                    val fragmentD = ScrapFragment()
                    transaction.replace(R.id.main_frame, fragmentD, "scrap")
                }
                R.id.action_mypage -> {
                    val fragmentE = MyPageFragment()
                    transaction.replace(R.id.main_frame, fragmentE, "page")
                }
            }
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
            true
        }

        fab_write.setOnClickListener {
            WriteFragment().show(supportFragmentManager, "BottomSheetEx")
        }

    }

    override fun onOptionClick(text: String) {
        TODO("Not yet implemented")
        //change text on each item click
        textView.text = text
    }

    override fun onBackPressed() {
        val mBottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_nav)
        if (mBottomNavigationView.selectedItemId == R.id.action_menu) {
            super.onBackPressed()
            finish()
        } else {
            mBottomNavigationView.selectedItemId = R.id.action_menu
        }
    }
}

