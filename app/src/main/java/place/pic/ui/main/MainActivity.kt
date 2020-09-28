package place.pic.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import place.pic.R

import place.pic.ui.util.showToast
import place.pic.ui.main.home.HomeFragment
import place.pic.ui.main.bookmark.BookmarksFragment
import place.pic.ui.main.home.HomeFragmentForUT
import place.pic.ui.main.mypage.MyPageFragment
import place.pic.ui.main.place.PlacesFragment
import place.pic.ui.main.userlist.UserListFragment
import place.pic.ui.util.showToast


class MainActivity : AppCompatActivity(), WriteFragment.BottomSheetListener {

    var mBackWait: Long = 0

    interface OnBackPressed {
        fun onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tb: BottomNavigationView = findViewById(R.id.bottom_nav)
        tb.visibility = View.VISIBLE

        //처음 시작 화면 고정
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, HomeFragmentForUT())
            .commit()

        //바텀 네비게이션 구현
        bottom_nav.setOnNavigationItemSelectedListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            when (it.itemId) {
                R.id.action_home -> {
                    val fragmentD = HomeFragmentForUT()
                    transaction.replace(R.id.main_frame, fragmentD, "home")
                }
                R.id.action_menu -> {
                    val fragmentA = PlacesFragment()
                    transaction.replace(R.id.main_frame, fragmentA, "name")
                }
                R.id.action_people -> {
                    val fragmentB = UserListFragment()
                    transaction.replace(R.id.main_frame, fragmentB, "people")
                }
                /*R.id.action_scrap -> {
                    val fragmentD = BookmarksFragment()
                    transaction.replace(R.id.main_frame, fragmentD, "scrap")
                }*/
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
    }

    override fun onBackPressed() {
        val mBottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        if (System.currentTimeMillis() - mBackWait >= 2000) {
            mBackWait = System.currentTimeMillis()
            if (mBottomNavigationView.selectedItemId == R.id.action_home) {
                mBottomNavigationView.selectedItemId = R.id.action_home
                super.onBackPressed()
            } else {
                mBottomNavigationView.selectedItemId = R.id.action_home
            }
            showToast("뒤로가기 버튼을 한번 더 누르면 종료됩니다.")
        } else {
            finish() //액티비티 종료
        }
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mypage_bookmark, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}

