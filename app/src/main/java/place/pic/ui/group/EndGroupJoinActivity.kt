package place.pic.ui.group

import android.content.Intent
import android.graphics.Typeface.BOLD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_end_group_join.*
import place.pic.R

class EndGroupJoinActivity : AppCompatActivity() {

    private var groupName:String = ""
    private var groupImage:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_group_join)
        groupName = intent.getStringExtra("groupName")!!
        groupImage =intent.getStringExtra("groupImage")!!
        init()
    }

    private fun init() {
        bindEndGroupJoin()
        buttonEventMapping()
    }

    private fun buttonEventMapping(){
        btn_goto_group_list.setOnClickListener {
            val gotoGroupListIntent = Intent(this, GroupListActivity::class.java)
            startActivity(gotoGroupListIntent)
            finish()
        }
    }

    private fun bindEndGroupJoin(){
        Glide.with(this).load(groupImage).into(img_end_group_profile)
        val endGroupJoinText = SpannableStringBuilder("$groupName 가입 신청이\n 완료되었습니다.")
        endGroupJoinText.setSpan(
            StyleSpan(BOLD),
            0,
            groupName.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        tv_end_group_result.text = endGroupJoinText
        tv_end_group_comment.text = "관리자의 승인 이후 그룹에 참여하실 수 있습니다.\n 승인 결과는 푸시알림으로 알려드립니다."
    }
}