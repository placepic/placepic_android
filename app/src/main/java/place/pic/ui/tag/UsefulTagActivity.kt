package place.pic.ui.tag

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_useful_tag.*
import place.pic.R
import place.pic.data.entity.UsefulTag
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By kimdahyee
 * on 7월 07일, 2020
 */

class UsefulTagActivity : AppCompatActivity() {

    private val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjMsIm5hbWUiOiLstZzsmIHtm4giLCJpYXQiOjE1OTM2OTkxODMsImV4cCI6MTU5NjI5MTE4MywiaXNzIjoicGxhY2VwaWMifQ.rmFbeBfviyEzbMlMM4b3bMMiRcNDDbiX8bQtwL_cuN0"
    private val contentType: String = "application/json"

    private val usefulTagList = mutableListOf<UsefulTag>()
    private val usefulTagChipList = mutableListOf<Chip>()
    private val placePicService = PlacePicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_useful_tag)

        val intent = intent
        val categoryIdx = intent.getIntExtra("categoryIdx", 1)
        val tagListForUpdate: ArrayList<UsefulTag> =
            intent.getSerializableExtra("checkedChipIntent") as ArrayList<UsefulTag>

        getConnection(categoryIdx)

        //수정을 위해 click된 chip인지 확인
        checkChipForUpdate(tagListForUpdate)

        useful_tag_save.setOnClickListener { onSaveClick() }
    }

    private fun getConnection(categoryIdx: Int) {
        placePicService.getInstance()
            .requestUsefulTag(
                contentType,
                token,
                categoryIdx
            ).enqueue(object : Callback<BaseResponse<List<UsefulTagResponse>>> {
                override fun onFailure(
                    call: Call<BaseResponse<List<UsefulTagResponse>>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: Call<BaseResponse<List<UsefulTagResponse>>>,
                    response: Response<BaseResponse<List<UsefulTagResponse>>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        if (response.body()!!.success) {
                            for (i in response.body()!!.data.indices) {
                                var usefulTag = UsefulTag(
                                    tagIdx = response.body()!!.data[i].tagIdx,
                                    tagName = response.body()!!.data[i].tagName
                                )
                                usefulTagList.add(usefulTag)
                            }

                            val chipGroup = chipgroup_useful_tag
                            for (tags in usefulTagList) {
                                val chip =
                                    ChipFactory.newInstance(layoutInflater) //object method 호출하기
                                usefulTagChipList.add(chip)
                                chip.isClickable = true
                                chip.text = tags.tagName
                                chipGroup.addView(chip)
                            }

                            for (i in 0 until usefulTagChipList.size) { //chipList.forEach로도 가능
                                usefulTagChipList[i].setOnClickListener { view ->
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

    private fun checkChipForUpdate(tagListForUpdate: ArrayList<UsefulTag>) {
        for (i in 0 until usefulTagChipList.size) {
            if (usefulTagChipList[i].text == tagListForUpdate[i].tagName) {
                usefulTagChipList[i].isChecked = true
            }
        }
    }

    private fun changeButtonColor(colorString: String, drawable: Int, clickable: Boolean) {
        tv_useful_tag_save.setTextColor(Color.parseColor(colorString))
        useful_tag_save.setBackgroundResource(drawable)
        useful_tag_save.isClickable = clickable
    }

    private fun checkChipIsChecked(): Boolean {
        var checkResult = false
        for (i in 0 until usefulTagChipList.size) {
            if (usefulTagChipList[i].isChecked) //true
                checkResult = true
        }
        return checkResult
    }

    private fun onSaveClick() {
        var checkedTagList = ArrayList<UsefulTag>()

        for (i in 0 until usefulTagChipList.size) {
            if (usefulTagChipList[i].isChecked) {
                var usefulTag = UsefulTag(usefulTagList[i].tagIdx, usefulTagList[i].tagName)
                checkedTagList.add(usefulTag)
            }
        }

        val checkedChipIntent = Intent()
        checkedChipIntent.putExtra("checkedChip", checkedTagList)
        setResult(Activity.RESULT_OK, checkedChipIntent)
    }
}