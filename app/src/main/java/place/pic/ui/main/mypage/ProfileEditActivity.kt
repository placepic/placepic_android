package place.pic.ui.main.mypage

import android.Manifest.*
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile_edit.*
import kotlinx.android.synthetic.main.item_like_user_list.*
import place.pic.R
import place.pic.ui.util.customTextChangedListener
import place.pic.ui.util.showToast

class ProfileEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

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

        val user_part=""
        et_profile_setting_in_part.customTextChangedListener {
            if (it != user_part){
                btn_profile_edit_ok.isEnabled = true
                btn_profile_edit_ok.setTextColor(getColor(R.color.pinkF6))
                return@customTextChangedListener
            }
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
                if (grantResults.size>0 && grantResults[0] ==
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


}

