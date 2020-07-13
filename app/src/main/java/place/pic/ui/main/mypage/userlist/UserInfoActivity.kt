package place.pic.ui.main.mypage.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_user_info.*
import place.pic.R
import place.pic.data.remote.response.ResponseWaitUser

class UserInfoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        val userInfo = intent.getParcelableExtra<ResponseWaitUser>("WaitUser")!!
        init(userInfo)
    }
    private fun init(userInfo:ResponseWaitUser){
        bindUserInfo(userInfo)
    }

    private fun buttonEventMapping(){
        img_user_info_top_back_btn.setOnClickListener(this)
        btn_user_reject.setOnClickListener(this)
        btn_user_accept.setOnClickListener(this)
    }

    private fun bindUserInfo(userInfo:ResponseWaitUser){
        tv_user_info_name.text = userInfo.userName
        tv_user_info_group.text = userInfo.part
        tv_user_info_birth.text = userInfo.userBirth
        tv_user_info_phone.text = userInfo.phoneNumber
        tv_user_info_gender.text= selectGender(userInfo.gender)
    }

    private fun selectGender(gender: Int):String{
        return when (gender) {
            GENDER_MAN -> "남성"
            GENDER_GIRL -> "여성"
            GENDER_NON -> "기타"
            else -> throw IllegalArgumentException("잘못된 접근입니다.")
        }
    }

    override fun onClick(btn: View?) {
        when (btn!!.id) {
            R.id.img_user_info_top_back_btn ->{
                onBackPressed()
            }
            R.id.btn_user_reject ->{

            }
            R.id.btn_user_accept->{

            }
        }
    }

    companion object{
        private const val GENDER_MAN = 0
        private const val GENDER_GIRL = 1
        private const val GENDER_NON = 2
    }
}