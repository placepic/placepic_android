package place.pic.ui.main.mypage

import android.content.Context
import android.content.Intent
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pagerAdapter = MyPagerAdapter(childFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_my_page, container, false)
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
                        user_write_count = response.body()!!.data.postCount

                        tv_profile_name.text = user_name
                        tv_profile_intro.text = user_part
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
            }
        })

        /*v.cl_user_list.setOnClickListener {
            val intent = Intent(context, WaitUserListActivity::class.java)
            startActivity(intent)
        }*/

        v.bt_setting.setOnClickListener {
            val intent2 = Intent(context, MyPageSettingActivity::class.java)
            startActivity(intent2)
            //activity?.finish()
        }
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


