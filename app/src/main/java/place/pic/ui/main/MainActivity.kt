package place.pic.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import place.pic.R
import place.pic.ui.main.place.PlacesFragment

class MainActivity : AppCompatActivity(){
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
        bottom_nav.setOnNavigationItemSelectedListener{
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            when(it.itemId){
                R.id.action_menu -> {
                    val fragmentA = PlacesFragment()
                    transaction.replace(R.id.main_frame, fragmentA, "name")
                }
                R.id.action_people -> {
                    val fragmentB = PeopleFragment()
                    transaction.replace(R.id.main_frame, fragmentB, "people")
                }
                R.id.action_write -> {
                    val fragmentC = WriteFragment()
                    transaction.replace(R.id.main_frame, fragmentC, "write")
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
    }
}

