package place.pic.ui.main.mypage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import kotlinx.android.synthetic.main.item_user_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.MyPageResponse
import place.pic.ui.main.bookmark.BookmarksFragment
import place.pic.ui.main.place.PlacesViewModel
import place.pic.ui.main.place.adapter.PlacesPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import place.pic.ui.login.LoginActivity
import place.pic.ui.main.MainActivity


class MyPageFragment : Fragment() {
    private lateinit var pagerAdapter: MyPagerAdapter
    companion object {
        fun newInstance(): MyPageFragment {
            return MyPageFragment()
        }
    }

    var user_name: String? = null
    var user_state: Int? = null
    var user_part: String? = null
    var user_image: String? = null
    var user_write_count: Int? = null
    var user_post_count: Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pagerAdapter = MyPagerAdapter(childFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_my_page, container, false)
        PlacepicAuthRepository.getInstance(requireContext()).saveUserToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjE4OCwicGhvbmVOdW1iZXIiOiIwMTA1NDA5OTg1OSIsImlhdCI6MTYwMDY2Mzk0NSwiZXhwIjoxNjA1ODQ3OTQ1LCJpc3MiOiJwbGFjZXBpYyJ9.ZlLonyyYdGye3JECXpkk_FHd3UonwS6QDl4sziDGB6g")
        PlacepicAuthRepository.getInstance(requireContext()).saveGroupId(17)
        PlacePicService.getInstance().requestMyPage(
            token = PlacepicAuthRepository.getInstance(requireContext()).userToken!!,
            groupIdx = PlacepicAuthRepository.getInstance(requireContext()).groupId!!
        ).enqueue(object : Callback<BaseResponse<MyPageResponse>> {
            override fun onFailure(
                call: Call<BaseResponse<MyPageResponse>>,
                t: Throwable
            ) {
                //통신실패
            }

            override fun onResponse(
                call: Call<BaseResponse<MyPageResponse>>,
                response: Response<BaseResponse<MyPageResponse>>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.success) {

                        user_name = response.body()!!.data.userName
                        user_state = response.body()!!.data.state
                        user_part = response.body()!!.data.part
                        user_image = response.body()!!.data.userImage
                        user_post_count=response.body()!!.data.postCount
                        user_write_count = response.body()!!.data.writeCount


                        tv_profile_name?.text = user_name
                        tv_profile_intro?.text = user_part
                        //tv_count_im_write.text = user_write_count.toString()

                        Glide.with(img_profile).load(user_image).into(img_profile)

                        when (user_state) {
                            0 -> {
                                tv_profile_kind.text = "관리자"
                                //cl_user_list.visibility=View.VISIBLE
                            }
                            1 -> {
                                tv_profile_kind.text = "멤버"
                                //cl_user_list.visibility=View.INVISIBLE
                            }
                            else -> tv_profile_kind.text = "승인대기중"
                        }
                    }
                }
                else
                {
                    user_name="이수정"
                    user_part="안드지롱"
                    user_image="https://images.homify.com/c_fill,f_auto,q_0,w_740/v1495806612/p/photo/image/2031170/%C3%A3_-_%C3%A3%C3%B5_%C3%8E_%C3%9B%C3%80%C3%BA%C3%81%C3%95%C3%81_%C3%BA_8.jpg"
                    user_state=0
                }
            }
        })



        v.bt_setting.setOnClickListener {
            val preferences = context?.getSharedPreferences("mypageuser", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor= preferences!!.edit()

            editor.putString("mp_user_name",user_name)
            editor.putString("mp_user_part",user_part)
            editor.putString("mp_user_image",user_image)
            editor.putInt("mp_user_state",user_state!!)


            editor.clear()
            editor.apply()
            editor.commit()

            val intent2 = Intent(context, MyPageSettingActivity::class.java)
            startActivity(intent2)
            //intent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }
        /*v.cl_user_list.setOnClickListener {
            val intent = Intent(context, WaitUserListActivity::class.java)
            startActivity(intent)
        }*/


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vp_mypage.adapter = pagerAdapter

        vp_mypage.addOnPageChangeListener( TabLayout.TabLayoutOnPageChangeListener(tab))
        tab.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) { }
            override fun onTabUnselected(tab: TabLayout.Tab) { }
            override fun onTabReselected(tab: TabLayout.Tab) { }
        })
        tab.setupWithViewPager(vp_mypage)
    }
}


