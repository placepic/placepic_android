package place.pic.ui.tag

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_useful_tag.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Place
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

    private val usefulTagList = mutableListOf<UsefulTag>()
    private val usefulTagChipList = mutableListOf<Chip>()
    private var tagListForUpdate = mutableListOf<UsefulTag>()

    private val placePicService = PlacePicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_useful_tag)

        val intent = intent
        val categoryIdx: Place.Type = intent.getSerializableExtra("categoryIdx") as Place.Type

        getTagListFromServer(categoryIdx)

        useful_tag_save.setOnClickListener { onSaveClick() }

        img_back4444.setOnClickListener { onBackPressed() }
    }

    private fun getAlreadySelectedTags() {
        val selectedChipIntent: Intent = intent

        tagListForUpdate = (selectedChipIntent.getSerializableExtra("checkedChipIntent")
            ?: return) as MutableList<UsefulTag>
        //elbis  ?: null이면 : 뒤에를 실행해라

        if (tagListForUpdate.isEmpty()) {
            return
        } else {
            checkChipForUpdate(tagListForUpdate)
        }
    }

    private fun checkChipForUpdate(tagListForUpdate: MutableList<UsefulTag>) {
        for (i in 0 until usefulTagChipList.size) {
            for (j in 0 until tagListForUpdate.size) {
                if (usefulTagChipList[i].text == tagListForUpdate[j].tagName) {
                    usefulTagChipList[i].isChecked = true
                }
            }
        }
    }

    private fun getTagListFromServer(categoryIdx: Place.Type) {

        val token = PlacepicAuthRepository.getInstance(this).userToken ?: return

        placePicService.getInstance()
            .requestUsefulTag(
                token = token,
                categoryIdx = categoryIdx.position
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
                                val usefulTag = UsefulTag(
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
                                usefulTagChipList[i].setOnClickListener {
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
                            getAlreadySelectedTags()
                        }
                    }
                }

            })
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
        //선택된 chip 관련 정보 ArrayList<UsefulTag>에 넣어서 전달
        val checkedTagList = ArrayList<UsefulTag>()

        for (i in 0 until usefulTagChipList.size) {
            if (usefulTagChipList[i].isChecked) {
                val usefulTag = UsefulTag(usefulTagList[i].tagIdx, usefulTagList[i].tagName)
                checkedTagList.add(usefulTag)
                Log.d("selected checking", usefulTag.toString())
            }
        }

        if (checkedTagList.size > 3) {
            Toast.makeText(this, "최대 3개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
        } else {
            val checkedChipIntent = Intent()
            checkedChipIntent.putExtra("checkedChip", checkedTagList)
            setResult(Activity.RESULT_OK, checkedChipIntent)
            finish()
        }
    }

    companion object {
        const val REQUEST_CODE = 2004
    }
}