package place.pic.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search_subway.*
import place.pic.R
import place.pic.ui.main.mypage.userlist.UserListFragment
import place.pic.ui.main.place.PlacesFragment


class MainActivity : AppCompatActivity(),WriteFragment.BottomSheetListener {
    private val token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjMsIm5hbWUiOiLstZzsmIHtm4giLCJpYXQiOjE1OTM2OTkxODMsImV4cCI6MTU5NjI5MTE4MywiaXNzIjoicGxhY2VwaWMifQ.rmFbeBfviyEzbMlMM4b3bMMiRcNDDbiX8bQtwL_cuN0"

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
                    val fragmentB =
                        UserListFragment()
                    transaction.replace(R.id.main_frame, fragmentB, "people")
                }
                R.id.action_scrap -> {
                    val fragmentD = ScrapFragment()
                    transaction.replace(R.id.main_frame, fragmentD, "scrap")
                }
                R.id.action_mypage -> {
                    val fragmentE = MyPageFragment()
                    transaction.replace(R.id.main_frame, fragmentE, "page")
                    /*
                    PlacePicService.getInstance().requestMyPage(
                        MyPageRequest(
                            //그룹 인덱스
                        )).enqueue(object: Callback<BaseResponse<MyPageResponse>> {
                        override fun onFailure(call: Call<BaseResponse<MyPageResponse>>, t: Throwable) {
                            //통신실패
                        }
                        override fun onResponse(
                            call: Call<BaseResponse<MyPageResponse>>,
                            response: Response<BaseResponse<MyPageResponse>>
                        ) {
                            PlacepicAuthRepository
                                .getInstance(this@MainActivity)
                                .saveUserToken(response.body()!!.data.accessToken)
                            if(response.isSuccessful)
                            {
                                if(response.body()!!.success)
                                {
                                    val fragmentE = MyPageFragment()
                                    transaction.replace(R.id.main_frame, fragmentE, "page")
                                }
                            }
                            else {
                                //tv_login_fail.visibility = View.VISIBLE
                                //btn_login.isEnabled = false
                            }
                        }
                    })
                    */

                }
            }
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
            true
        }

        fab_write.setOnClickListener{
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

