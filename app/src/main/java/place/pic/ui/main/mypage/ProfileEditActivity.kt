package place.pic.ui.main.mypage

import android.Manifest.*
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_profile_edit.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.request.ProfileEditRequest
import place.pic.data.remote.response.BaseResponse
import place.pic.ui.upload.ImageUri
import place.pic.ui.util.customTextChangedListener
import place.pic.ui.util.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileEditActivity : AppCompatActivity() {

    var change_part: String?=null
    var change_image: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        val preferences = this.getSharedPreferences("mypageuser", Context.MODE_PRIVATE)

        img_profile_edit_top_bar_back_btn.setOnClickListener {
            finish()
        }

        img_profile_setting.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else{
                    pickImageFromGallery()
                }
            }
            else{
                pickImageFromGallery()
            }
        }

        Glide.with(img_profile_setting).load(preferences.getString("mp_user_image","")).into(img_profile_setting)
        tv_profile_setting_name.text = preferences.getString("mp_user_name","")
        et_profile_setting_in_part.setText(preferences.getString("mp_user_part","").toString())

        et_profile_setting_in_part.customTextChangedListener {
            if (it != et_profile_setting_in_part){
                btn_profile_edit_ok.isEnabled = true
                btn_profile_edit_ok.setTextColor(getColor(R.color.pinkF6))
                return@customTextChangedListener
            }
        }

        btn_profile_edit_ok.setOnClickListener {
            change_part=et_profile_setting_in_part.text.toString()
            //val uri=getURLForResource(img_profile_setting)
            //change_image= ImageUri(uri!!).toMultipart(this).toString()
            change_image=img_profile_setting.toString()
            PlacePicService.getInstance().profileEditRequest(
                token = PlacepicAuthRepository.getInstance(this).userToken!!,
                groupIdx = PlacepicAuthRepository.getInstance(this).groupId!!,
                body = ProfileEditRequest(
                    userImage =change_image!!,
                    part = change_part!!
                )
            ).enqueue(object :Callback<BaseResponse<Unit>> {
                override fun onResponse(
                    call: Call<BaseResponse<Unit>>,
                    response: Response<BaseResponse<Unit>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.success) {
                            showToast(change_part.toString())
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    showToast("권한을 확인하여 주세요")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            img_profile_setting.setImageURI(data?.data)
        }
    }

    private fun getURLForResource(id: CircleImageView): Uri? {
        return Uri.parse("android.resource://$packageName/$id")
    }
}

