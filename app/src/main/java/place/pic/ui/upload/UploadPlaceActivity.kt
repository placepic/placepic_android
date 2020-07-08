package place.pic.ui.upload

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import place.pic.R
import place.pic.data.entity.Subway
import place.pic.databinding.ActivityUploadPlaceBinding
import place.pic.ui.main.place.PlacesFragment.Companion.SUBWAYS_KEY
import place.pic.ui.search.subway.SubwaySearchActivity
import place.pic.ui.upload.adapter.ImagesToUploadAdapter
import place.pic.ui.upload.adapter.SubwaysAdapter

class UploadPlaceActivity : AppCompatActivity() {

    private val uploadPlacesViewModel = UploadPlaceViewModel()
    private lateinit var imagesAdapter: ImagesToUploadAdapter
    private lateinit var subwaysAdapter: SubwaysAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityUploadPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
        subscribeToastMessages()
        subscribeImages()
        subscribeSubways()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGES) {
            if (resultCode == Activity.RESULT_OK) {
                uploadPlacesViewModel.handleImageUris(data)
            }
        }
        if (requestCode == SubwaySearchActivity.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                uploadPlacesViewModel.handleSubwaysIntent(data)
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

    private fun showPermissionRejected() {
        Toast.makeText(this, R.string.galleryPermissionRejected, Toast.LENGTH_SHORT).show()
    }

    private fun initView(binding: ActivityUploadPlaceBinding) {
        binding.lifecycleOwner = this
        binding.viewModel = uploadPlacesViewModel
        imagesAdapter = ImagesToUploadAdapter().apply { submitList(emptyList()) }
        imagesAdapter.setGettingImageButtonListener { deployGalleryOrRequestPermission() }
        imagesAdapter.setOnImageDeleteListener { uploadPlacesViewModel.deleteImageUri(it) }
        binding.rvPhotos.adapter = imagesAdapter
        subwaysAdapter = SubwaysAdapter()
        binding.rvSubways.adapter = subwaysAdapter
        binding.btnBack.setOnClickListener { finish() }
        binding.btnSelectSubways.setOnClickListener { deploySelectSubwaysActivity() }
        binding.btnModifySubways.setOnClickListener { deploySelectSubwaysActivity() }
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

    private fun subscribeSubways() {
        uploadPlacesViewModel.subways.observe(this, Observer {
            subwaysAdapter.submitList(it)
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

    private fun deploySelectSubwaysActivity() {
        val intent = Intent(this, SubwaySearchActivity::class.java)
        val alreadySelectedSubways = uploadPlacesViewModel.subways.value ?: emptyList()
        intent.putExtra(SUBWAYS_KEY, ArrayList<Subway>(alreadySelectedSubways))
        startActivityForResult(intent, SubwaySearchActivity.REQUEST_CODE)
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGES = 3000
        const val REQUEST_CODE_GALLERY_PERMISSION = 3001
    }
}
