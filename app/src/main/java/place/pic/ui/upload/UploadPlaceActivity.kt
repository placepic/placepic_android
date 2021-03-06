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
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.databinding.ActivityUploadPlaceBinding
import place.pic.ui.dialog.SimpleDialog
import place.pic.ui.main.MainActivity
import place.pic.ui.main.place.PlacesFragment.Companion.SUBWAYS_KEY
import place.pic.ui.search.subway.SubwaySearchActivity
import place.pic.ui.tag.KeywordTagActivity
import place.pic.ui.tag.UsefulTagActivity
import place.pic.ui.upload.adapter.ImagesToUploadAdapter
import place.pic.ui.upload.adapter.SubwaysAdapter

class UploadPlaceActivity : AppCompatActivity() {

    private lateinit var uploadPlacesViewModel: UploadPlaceViewModel
    private lateinit var binding: ActivityUploadPlaceBinding
    private lateinit var keywordChips: KeywordChips
    private lateinit var featureChips: FeatureChips
    private lateinit var imagesAdapter: ImagesToUploadAdapter
    private lateinit var subwaysAdapter: SubwaysAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uploadPlacesViewModel = UploadPlaceViewModel(PlacepicAuthRepository.getInstance(this))
        binding = ActivityUploadPlaceBinding.inflate(layoutInflater)
        keywordChips = KeywordChips(binding.chipGroupKeywords)
        featureChips = FeatureChips(binding.chipGroupFeatures)
        initView()
        setContentView(binding.root)
        uploadPlacesViewModel.handlePreviousActivityRequestParams(intent)
        subscribeToastMessages()
        subscribeImages()
        subscribeSubways()
        subscribeKeywords()
        subscribeFeatures()
        subscribeUploadSuccessEvent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGES -> uploadPlacesViewModel.handleImageUris(data)
                SubwaySearchActivity.REQUEST_CODE -> uploadPlacesViewModel.handleSubwaysIntent(data)
                KeywordTagActivity.REQUEST_CODE -> uploadPlacesViewModel.handleKeywordsIntent(data)
                UsefulTagActivity.REQUEST_CODE -> uploadPlacesViewModel.handleFeaturesIntent(data)
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

    override fun onBackPressed() {
        SimpleDialog(this).apply {
            setContent(R.string.writing_data_will_be_deleted)
            setCancelClickListener(R.string.write_cancel) { dismiss(); super.onBackPressed() }
            setOkClickListener(R.string.close) { dismiss() }
        }.show()
    }

    private fun showPermissionRejected() {
        Toast.makeText(this, R.string.galleryPermissionRejected, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        binding.lifecycleOwner = this
        binding.viewModel = uploadPlacesViewModel
        imagesAdapter = ImagesToUploadAdapter().apply { submitList(emptyList()) }
        imagesAdapter.setGettingImageButtonListener { deployGalleryOrRequestPermission() }
        imagesAdapter.setOnImageDeleteListener { uploadPlacesViewModel.deleteImageUri(it) }
        binding.rvPhotos.adapter = imagesAdapter
        subwaysAdapter = SubwaysAdapter()
        binding.rvSubways.adapter = subwaysAdapter
        binding.btnBack.setOnClickListener { onBackPressed() }
        binding.btnSelectSubways.setOnClickListener { deploySelectSubwaysActivity() }
        binding.btnModifySubways.setOnClickListener { deploySelectSubwaysActivity() }
        binding.btnSubways.setOnClickListener { deploySelectSubwaysActivity() }
        binding.btnSelectKeywords.setOnClickListener { deploySelectKeywordsActivity() }
        binding.btnModifyKeywords.setOnClickListener { deploySelectKeywordsActivity() }
        binding.btnKeywords.setOnClickListener { deploySelectKeywordsActivity() }
        binding.btnSelectFeatures.setOnClickListener { deploySelectFeaturesActivity() }
        binding.btnModifyFeatures.setOnClickListener { deploySelectFeaturesActivity() }
        binding.btnFeatures.setOnClickListener { deploySelectFeaturesActivity() }
        binding.btnSubmit.setOnClickListener { askUploadPlace() }
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

    private fun subscribeKeywords() {
        uploadPlacesViewModel.keywords.observe(this, Observer {
            keywordChips.submitKeywords(it)
        })
    }

    private fun subscribeFeatures() {
        uploadPlacesViewModel.features.observe(this, Observer {
            featureChips.submitFeatures(it)
        })
    }

    private fun subscribeUploadSuccessEvent() {
        uploadPlacesViewModel.successEvent.observe(this, Observer { isSuccess ->
                if (isSuccess) successUpload()
        })
    }

    private fun deployGalleryOrRequestPermission() {
        val previousPermissionGranted = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (previousPermissionGranted == PackageManager.PERMISSION_GRANTED) {
            deployGallery()
            return
        }
        requestPermissions(
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE_GALLERY_PERMISSION
        )
    }

    private fun deployGallery() {
        val photoPickerIntent = Intent().apply {
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

    private fun deploySelectKeywordsActivity() {
        val intent = Intent(this, KeywordTagActivity::class.java)
        val alreadySelectedKeywords = uploadPlacesViewModel.keywords.value ?: emptyList()
        intent.putExtra("checkedChip", ArrayList<KeywordTag>(alreadySelectedKeywords))
        intent.putExtra("categoryIdx", uploadPlacesViewModel.placeType.value!!)
        startActivityForResult(intent, KeywordTagActivity.REQUEST_CODE)
    }

    private fun deploySelectFeaturesActivity() {
        val intent = Intent(this, UsefulTagActivity::class.java)
        val alreadySelectedFeatures = uploadPlacesViewModel.features.value ?: emptyList()
        intent.putExtra("checkedChipIntent", ArrayList<UsefulTag>(alreadySelectedFeatures))
        intent.putExtra("categoryIdx", uploadPlacesViewModel.placeType.value!!)
        startActivityForResult(intent, UsefulTagActivity.REQUEST_CODE)
    }

    private fun uploadPlace() {
        uploadPlacesViewModel.uploadPlace(this)
    }

    private fun successUpload() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        Toast.makeText(this, R.string.upload_finished, Toast.LENGTH_SHORT).show()
    }

    private fun askUploadPlace() {
        SimpleDialog(this).apply {
            setContent(R.string.ask_upload_place)
            setCancelClickListener(R.string.cancel) { dismiss() }
            setOkClickListener(R.string.regist) { uploadPlace(); dismiss() }
        }.show()
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGES = 3000
        const val REQUEST_CODE_GALLERY_PERMISSION = 3001
    }
}
