package place.pic.ui.upload

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import place.pic.R
import place.pic.databinding.ActivityUploadPlaceBinding

class UploadPlaceActivity : AppCompatActivity() {

    private val uploadPlacesViewModel = UploadPlaceViewModel()
    private lateinit var imagesAdapter: ImagesToUploadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityUploadPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
        subscribeToastMessages()
        subscribeImages()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGES) {
            if (resultCode == Activity.RESULT_OK) {
                uploadPlacesViewModel.handleImageUris(data)
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
        imagesAdapter.setOnImageDeleteListener { uploadPlacesViewModel.deleteImageUri(it) }
        binding.rvPhotos.adapter = imagesAdapter
    }

    private fun subscribeToastMessages() {
        uploadPlacesViewModel.toastEvent.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun subscribeImages() {
        uploadPlacesViewModel.imageUris.observe(this, Observer {
            imagesAdapter.submitList(it)
        })
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

    companion object {
        const val REQUEST_CODE_PICK_IMAGES = 3000
        const val REQUEST_CODE_GALLERY_PERMISSION = 3001
    }

}
