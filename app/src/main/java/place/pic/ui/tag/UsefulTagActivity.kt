package place.pic.ui.tag

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_useful_tag.*
import place.pic.R
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

    private val restaurantUsefulTagList = mutableListOf<String>()
    private val usefulTagChipList = mutableListOf<Chip>()
    private val placePicService = PlacePicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_useful_tag)

        getConnection()
    }

    private fun getConnection() {
        placePicService.getInstance()
            .requestUsefulTag(
                token,
                1
            ).enqueue(object : Callback<BaseResponse<List<UsefulTagData>>> {
                override fun onFailure(
                    call: Call<BaseResponse<List<UsefulTagData>>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: Call<BaseResponse<List<UsefulTagData>>>,
                    response: Response<BaseResponse<List<UsefulTagData>>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        if (response.body()!!.success) {
                            Log.d("response data check", "${response.body()?.data.toString()}")

                            for (i in response.body()!!.data.indices) {
                                restaurantUsefulTagList.add(i, response.body()!!.data[i].tagName)
                                Log.d("태그네임확인", "${response.body()!!.data[i].tagName}")
                                Log.d("태그리스트확인", "${restaurantUsefulTagList.get(i)}")
                            }

                            val chipGroup = chipgroup_useful_tag
                            for (tags in restaurantUsefulTagList) {
                                val chip =
                                    ChipFactory.newInstance(layoutInflater) //object method 호출하기
                                usefulTagChipList.add(chip)
                                chip.isClickable = true
                                chip.text = tags
                                Log.d("키워드 칩 확인", "${tags}")
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

    fun onClick(view: View) {
        Toast.makeText(this@UsefulTagActivity, "클릭 가능", Toast.LENGTH_SHORT).show()
    }
}