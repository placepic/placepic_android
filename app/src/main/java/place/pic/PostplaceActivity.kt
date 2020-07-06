package place.pic

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_postplace.*
import place.pic.ui.main.MainActivity


class PostplaceActivity : AppCompatActivity(), View.OnClickListener {
    private val REQUEST_CODE_PICK_IMAGE = 1001
    var selectedImage = arrayListOf<Uri>()
    private lateinit var imageAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_postplace)
        init()

    }

    private fun init() {
        btn_photo.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_photo -> {
                openImageChooser()
            }
        }
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            it.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val selectedImageArrayList = arrayListOf<Uri>()
                if (data.clipData != null) {
                    val clipdata: ClipData = data.clipData!!
                    when {
                        clipdata.itemCount > 10 -> {
                            Toast.makeText(this, "사진은 10개까지 선택 가능합니다", Toast.LENGTH_SHORT).show()

                        }else -> {
                            Log.d("test","멀티셀렉트 지원")
                            for (i in 0 until clipdata.itemCount) {
                                selectedImageArrayList.add(clipdata.getItemAt(i).uri)
                            }
                            selectedImage.clear()
                            selectedImage = selectedImageArrayList
                            setImageAndAdpater()
                    }
                    }
                }else{//멀티 선택 미지원 기기에서 clipData가 없음.
                    Log.d("test","멀티셀렉트 미지원")
                    selectedImageArrayList.add(data.data!!)
                    selectedImage = selectedImageArrayList
                    setImageAndAdpater()
                }

        }
    }

    private fun setImageAndAdpater() {
        imageAdapter = PhotoAdapter(selectedImage,this)
        rv_photo_list.adapter = imageAdapter
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
        finish()
    }
}
