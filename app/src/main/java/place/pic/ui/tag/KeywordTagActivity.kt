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
import place.pic.data.entity.KeywordTag
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.KeywordTagData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By kimdahyee
 * on 7월 06일, 2020
 */

class KeywordTagActivity : AppCompatActivity() {

    private val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjMsIm5hbWUiOiLstZzsmIHtm4giLCJpYXQiOjE1OTM2OTkxODMsImV4cCI6MTU5NjI5MTE4MywiaXNzIjoicGxhY2VwaWMifQ.rmFbeBfviyEzbMlMM4b3bMMiRcNDDbiX8bQtwL_cuN0"

    private val keywordTagList = mutableListOf<KeywordTag>()
    private val keywordTagChipList = mutableListOf<Chip>()
    private val placePicService = PlacePicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword_tag)

        //categoryIdx 꺼내기
        val intent = intent
        val categoryIdx = intent.getIntExtra("categoryIdx", 1)
        val tagListForUpdate: ArrayList<KeywordTag> =
            intent.getSerializableExtra("checkedChipIntent") as ArrayList<KeywordTag>

        getConnection(categoryIdx)

        //수정을 위해 click된 chip인지 확인
        checkChipForUpdate(tagListForUpdate)

        keyword_tag_save.setOnClickListener { onSaveClick() }
    }

    private fun getConnection(categoryIdx: Int) { //getConnection(categoryIdx: Int)
        placePicService.getInstance()
            .requestKeywordTag(
                token,
                2
                //categoryIdx
            ).enqueue(object : Callback<BaseResponse<List<KeywordTagData>>> {
                override fun onFailure(
                    call: Call<BaseResponse<List<KeywordTagData>>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: Call<BaseResponse<List<KeywordTagData>>>,
                    response: Response<BaseResponse<List<KeywordTagData>>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        if (response.body()!!.success) {
                            response
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

    private fun checkChipForUpdate(tagListForUpdate: ArrayList<KeywordTag>) {
        for (i in 0 until keywordTagChipList.size) {
            if (keywordTagChipList[i].text == tagListForUpdate[i].tagName) {
                keywordTagChipList[i].isChecked = true
            }
        }
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
            }
        }

        val checkedChipIntent = Intent()
        checkedChipIntent.putExtra("checkedChip", checkedTagList)
        setResult(Activity.RESULT_OK, checkedChipIntent)
    }
}

