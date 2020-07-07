package place.pic.ui.upload

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import place.pic.R
import place.pic.databinding.ActivityUploadPlaceBinding


class UploadPlaceActivity : AppCompatActivity() {

    private lateinit var imagesAdapter: ImagesToUploadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityUploadPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGES) {
            if (resultCode == Activity.RESULT_OK) {
                loadImages(ImageUriExtractor.from(data))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_GALLERY_PERMISSION) {
            val isPermissionGranted =
                grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            if (isPermissionGranted) {
                deployGallery()
                return
            }
            showPermissionRejected()
        }
    }

    private fun initView(binding: ActivityUploadPlaceBinding) {
        imagesAdapter = ImagesToUploadAdapter().apply { submitList(emptyList()) }
        imagesAdapter.setGettingImageButtonListener { deployGalleryOrRequestPermission() }
        imagesAdapter.setOnImageDeleteListener { }
        binding.rvPhotos.adapter = imagesAdapter
    }

    private fun deployGalleryOrRequestPermission() {
        val previousPermissionGranted = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val isPermissionGranted = previousPermissionGranted == PackageManager.PERMISSION_GRANTED
        if (isPermissionGranted) {
            deployGallery()
            return
        }
        requestPermissions(
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE_GALLERY_PERMISSION
        )
    }

    private fun deployGallery() {
        val photoPickerIntent = Intent()
        photoPickerIntent.apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        startActivityForResult(
            Intent.createChooser(photoPickerIntent, "select picture"),
            REQUEST_CODE_PICK_IMAGES
        )
    }

    private fun showPermissionRejected() {
        Toast.makeText(this, R.string.galleryPermissionRejected, Toast.LENGTH_SHORT).show()
    }

    private fun loadImages(images: List<ImageUri>) {
        imagesAdapter.submitList(images)
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGES = 3000
        const val REQUEST_CODE_GALLERY_PERMISSION = 3001
    }

}
