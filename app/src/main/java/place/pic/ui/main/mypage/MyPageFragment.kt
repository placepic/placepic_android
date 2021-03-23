package place.pic.ui.main.mypage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.MyPageResponse
import place.pic.databinding.FragmentMyPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Url


class MyPageFragment : Fragment() {
    private lateinit var pagerAdapter: MyPagerAdapter
    private lateinit var binding: FragmentMyPageBinding
    companion object {
        fun newInstance(): MyPageFragment {
            return MyPageFragment()
        }
    }

    var user_name: String? = null
    var user_state: Int? = 2
    var user_part: String? = null
    var user_image: String? = null
    var user_write_count: Int? = null
    var user_post_count: Int? = null
    var group_name : String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pagerAdapter = MyPagerAdapter(childFragmentManager)
    }

    override fun onResume() {
        super.onResume()
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
                        user_post_count = response.body()!!.data.postCount
                        user_write_count = response.body()!!.data.bookMarkCnt
                        group_name=response.body()!!.data.groupName

                        binding.tvProfileName.text = user_name
                        binding.tvProfileIntro.text = user_part
                        binding.tvGroupName.text=group_name

                        (binding.tabMypage.getTabAt(0))?.text = "작성한 글 "+user_post_count?.toString()
                        (binding.tabMypage.getTabAt(1))?.text = "저장한 장소 "+user_write_count?.toString()

                        Glide.with(img_profile).load(user_image).into(img_profile)

                        when (user_state) {
                            0 -> {
                                binding.tvProfileKind.text = "관리자"
                            }
                            1 -> {
                                binding.tvProfileKind.text = "멤버"
                            }
                            else -> binding.tvProfileKind.text = "승인대기중"
                        }
                    }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_my_page,container, false)
        binding.myPageFragment=this
        binding.lifecycleOwner=this
        binding.btSetting.setOnClickListener {
            val preferences = context?.getSharedPreferences("mypageuser", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = preferences!!.edit()

            editor.putString("mp_user_name", user_name)
            editor.putString("mp_user_part", user_part)
            editor.putString("mp_user_image", user_image)
            user_state?.let { it1 -> editor.putInt("mp_user_state", it1) }

            editor.clear()
            editor.apply()
            editor.commit()

            val intent2 = Intent(context, MyPageSettingActivity::class.java)
            startActivity(intent2)
        }
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpMypage.adapter = pagerAdapter
        binding.vpMypage.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_mypage))
        binding.tabMypage.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.tabMypage.setupWithViewPager(vp_mypage)
    }
}


