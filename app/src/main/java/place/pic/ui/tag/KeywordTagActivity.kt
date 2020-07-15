package place.pic.ui.tag

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_keyword_tag.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.KeywordTag
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.KeywordTagResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By kimdahyee
 * on 7월 06일, 2020
 */

class KeywordTagActivity : AppCompatActivity() {

    private val keywordTagList = mutableListOf<KeywordTag>()
    private val keywordTagChipList = mutableListOf<Chip>()
    private val placePicService = PlacePicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword_tag)

        val intent = intent
        val categoryIdx = intent.getIntExtra("categoryIdx", 1)
        getAlreadySelectedTags(intent) //수정 시 사용자가 이전에 선택한 태그 가져오기

        getTagListFromServer(categoryIdx)
        keyword_tag_save.setOnClickListener { onSaveClick() }

        img_back444.setOnClickListener { onBackPressed() }
    }

    private fun getAlreadySelectedTags(intent: Intent) {
        val tagListForUpdate: MutableList<KeywordTag> =
            (intent.getSerializableExtra("chipIntent") ?: return) as MutableList<KeywordTag>
        //elbis  ?: null이면 : 뒤에를 실행해라
        checkChipForUpdate(tagListForUpdate) //수정을 위해 click된 chip인지 확인
    }

    private fun checkChipForUpdate(tagListForUpdate: MutableList<KeywordTag>) {
        for (i in 0 until keywordTagChipList.size) {
            if (keywordTagChipList[i].text == tagListForUpdate[i].tagName) {
                keywordTagChipList[i].isChecked = true
            }
        }
    }

    private fun getTagListFromServer(categoryIdx: Int) { //getConnection(categoryIdx: Int)

        val token = PlacepicAuthRepository.getInstance(this).userToken?:return

        placePicService.getInstance()
            .requestKeywordTag(
                token = token,
                categoryIdx = categoryIdx
            ).enqueue(object : Callback<BaseResponse<List<KeywordTagResponse>>> {
                override fun onFailure(
                    call: Call<BaseResponse<List<KeywordTagResponse>>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: Call<BaseResponse<List<KeywordTagResponse>>>,
                    response: Response<BaseResponse<List<KeywordTagResponse>>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        if (response.body()!!.success) {
                            for (i in response.body()!!.data.indices) {
                                var keywordTag = KeywordTag(
                                    tagIdx = response.body()!!.data[i].tagIdx,
                                    tagName = response.body()!!.data[i].tagName
                                )
                                keywordTagList.add(keywordTag)
                            }

                            val chipGroup = chipgroup_keyword_tag
                            for (tags in keywordTagList) {
                                val chip =
                                    ChipFactory.newInstance(layoutInflater) //object method 호출하기
                                keywordTagChipList.add(chip)
                                chip.isClickable = true
                                chip.text = tags.tagName
                                chipGroup.addView(chip)
                            }

                            for (i in 0 until keywordTagChipList.size) { //chipList.forEach로도 가능
                                keywordTagChipList[i].setOnClickListener {
                                    if (checkChipIsChecked()) {//true
                                        changeButtonColor(
                                            "#FFFFFF",
                                            R.drawable.rectangle_main_color,
                                            true
                                        )
                                    }

                                    if (!checkChipIsChecked()) {
                                        changeButtonColor(
                                            "#4F4F4F",
                                            R.drawable.rectangle_gray_f1f4f5,
                                            false
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            })
    }

    private fun changeButtonColor(colorString: String, drawable: Int, clickable: Boolean) {
        tv_tag_save.setTextColor(Color.parseColor(colorString))
        keyword_tag_save.setBackgroundResource(drawable)
        keyword_tag_save.isClickable = clickable
    }

    private fun checkChipIsChecked(): Boolean {
        var checkResult = false
        for (i in 0 until keywordTagChipList.size) {
            if (keywordTagChipList[i].isChecked) //true
                checkResult = true
        }
        return checkResult
    }

    private fun onSaveClick() {
        //선택된 chip 관련 정보 ArrayList<KeywordTag>에 넣어서 전달
        var checkedTagList = ArrayList<KeywordTag>()

        for (i in 0 until keywordTagChipList.size) {
            if (keywordTagChipList[i].isChecked) {
                var keywordTag = KeywordTag(keywordTagList[i].tagIdx, keywordTagList[i].tagName)
                checkedTagList.add(keywordTag)
                Log.d("selected checking", keywordTag.toString())
            }
        }

        val checkedChipIntent = Intent()
        checkedChipIntent.putExtra("checkedChip", checkedTagList)
        setResult(Activity.RESULT_OK, checkedChipIntent)
        finish()
    }

    companion object {
        const val REQUEST_CODE = 2003
    }

}

