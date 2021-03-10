package place.pic.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_my_page.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.OtherProfileResponse
import place.pic.databinding.ActivityUserProfileBinding
import place.pic.ui.main.mypage.mywritings.MyWritingsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserProfileActivity : AppCompatActivity() {
    var user_name: String? = null
    var user_state: Int? = 2
    var user_part: String? = null
    var user_image: String? = null
    var user_write_count: Int? = null
    var user_post_count: Int? = null

    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_user_profile)
        binding.userProfileActivity=this
        val intent: Intent? = null
        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().apply {
            add(R.id.fm_other_writings, MyWritingsFragment()).commit()
        }

        PlacePicService.getInstance().requestOtherProfile(
            token = PlacepicAuthRepository.getInstance(this).userToken!!,
            groupIdx = PlacepicAuthRepository.getInstance(this).groupId!!,
            userIdx = intent!!.getIntExtra("userIdx",-1)
        ).enqueue(object : Callback<BaseResponse<OtherProfileResponse>> {
            override fun onFailure(call: Call<BaseResponse<OtherProfileResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<BaseResponse<OtherProfileResponse>>,
                response: Response<BaseResponse<OtherProfileResponse>>
            ) {
                user_name = response.body()!!.data.userName
                user_state = response.body()!!.data.state
                user_part = response.body()!!.data.part
                user_image = response.body()!!.data.userImage
                user_post_count = response.body()!!.data.postCount

                binding.tvOtherProfileName.text = user_name
                binding.tvOtherProfileIntro.text = user_part
                binding.tvOtherWritingCount.text = user_post_count.toString()

                Glide.with(img_profile).load(user_image).into(img_profile)

                when (user_state) {
                    0 -> {
                        binding.tvOtherProfileKind.text = "관리자"
                    }
                    1 -> {
                        binding.tvOtherProfileKind.text = "멤버"
                    }
                    else -> binding.tvOtherProfileKind.text = "승인대기중"
                }
            }
        })
    }
}