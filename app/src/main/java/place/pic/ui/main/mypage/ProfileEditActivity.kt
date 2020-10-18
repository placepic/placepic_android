package place.pic.ui.main.mypage

import android.Manifest.permission
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.net.Uri.fromFile
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_profile_edit.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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
import java.io.File


class ProfileEditActivity : AppCompatActivity() {

    private lateinit var ProfileEditRequest : ProfileEditRequest
    var change_part: String?=null
    var change_image: MultipartBody.Part? = null
    lateinit var imageuri: List<ImageUri>
    var imgPath: String?=null
    var imageurlstring: String?=null
    var uri: Uri? =null


    @RequiresApi(Build.VERSION_CODES.O)
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

        Glide.with(img_profile_setting).load(preferences.getString("mp_user_image", "")).into(
            img_profile_setting
        )

        tv_profile_setting_name.text = preferences.getString("mp_user_name", "")
        et_profile_setting_in_part.setText(preferences.getString("mp_user_part", "").toString())

        et_profile_setting_in_part.customTextChangedListener {
            if (it != et_profile_setting_in_part){
                btn_profile_edit_ok.isEnabled = true
                btn_profile_edit_ok.setTextColor(getColor(R.color.pinkF6))
                return@customTextChangedListener
            }
        }

        btn_profile_edit_ok.setOnClickListener {
            change_part=et_profile_setting_in_part.text.toString()
            val requestBody2 = change_part?.toRequestBody("text/plain".toMediaType())
            if (requestBody2 != null) {
                MultipartBody.Part.createFormData("part", "part", requestBody2)
            }
            change_image=toMultipart(imgPath)

            PlacePicService.getInstance().profileEdit(
                token = PlacepicAuthRepository.getInstance(this).userToken!!,
                groupIdx = PlacepicAuthRepository.getInstance(this).groupId!!,
                part = requestBody2,
                profileImageUrl = change_image
            ).enqueue(object : Callback<BaseResponse<Unit>> {
                override fun onResponse(
                    call: Call<BaseResponse<Unit>>,
                    response: Response<BaseResponse<Unit>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.success) {
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    pickImageFromGallery()
                } else {
                    showToast("권한을 확인하여 주세요")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            uri = data?.data
            img_profile_setting.setImageURI(uri)
            data?.data?.let { imageurlstring=getPicture(this, it) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getPicture(context: Context, uri: Uri): String? {
        var index = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context.contentResolver.query(uri, proj, null, null, null)
        if (cursor == null) {
            imgPath=null
        } else if (cursor.moveToFirst()) {
            index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            imgPath = cursor.getString(index)
            Log.d("realPathFromURI2", "realPathFromURI: $imgPath")
            btn_profile_edit_ok.isEnabled = true
            btn_profile_edit_ok.setTextColor(getColor(R.color.pinkF6))
            cursor.close()
        } else {
            showToast("커서가 비었습니다.")
            imgPath=null
            cursor.close()
        }
        return imgPath
    }

    private fun toMultipart(context: String?): MultipartBody.Part? {
        if(context==null)
        {
            return null
        }
        else {
            val file = File(context)
            val requestBody = file.asRequestBody("image/jpeg".toMediaType())
            return MultipartBody.Part.createFormData("profileImageUrl", file.name, requestBody)
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }
}

