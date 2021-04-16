package place.pic.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.PlaceGridItem
import place.pic.data.entity.Profile
import place.pic.databinding.ActivityUserProfileBinding
import place.pic.ui.main.detail.DetailViewActivity
import place.pic.ui.main.place.adapter.PlaceGridItemsAdapter
import place.pic.ui.util.loadImageFrom


class UserProfileActivity : AppCompatActivity() {
    private val placeGridItemsAdapter by lazy { PlaceGridItemsAdapter() }
    private val userWritingsViewModel by lazy { UserProfileViewModel(PlacepicAuthRepository.getInstance(this))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placeGridItemsAdapter.setItemClickListener { onUserWritingClick(it) }
        binding.rvPlaceGridItems.adapter = placeGridItemsAdapter
        binding.lifecycleOwner = this
        binding.imgOtherProfileEditTopBarBackBtn.setOnClickListener { onBackPressed() }

        subscribeUserWritings()
        subscribeUserProfiles()
        userWritingsViewModel.requestUserWritings(getUserId())
    }

    private fun subscribeUserWritings() {
        userWritingsViewModel.userPlace.observe(this) {
            placeGridItemsAdapter.submitList(it)
        }
    }

    private fun subscribeUserProfiles(){
        userWritingsViewModel.profile.observe(this){
            updateProfile(it)
        }
    }

    private fun updateProfile(profile: Profile?) {
        profile?.userImage.let { findViewById<ImageView>(R.id.img_other_profile).loadImageFrom(it!!) }
        findViewById<TextView>(R.id.tv_other_profile_name).text = profile?.userName
        findViewById<TextView>(R.id.tv_other_profile_kind).text = profileState(profile?.state)
        findViewById<TextView>(R.id.tv_other_profile_intro).text = profile?.part
        findViewById<TextView>(R.id.tv_other_writing_count).text = profile?.postCount.toString()
    }

    private fun profileState(state: Int?): String {
        return if(state==0){
            "관리자"
        } else{
            "멤버"
        }
    }


    private fun onUserWritingClick(placeGridItem: PlaceGridItem) {
        val intent = Intent(this, DetailViewActivity::class.java)
        intent.putExtra("placeIdx", placeGridItem.placeIdx)
        startActivityForResult(intent, 5000)
    }

    private fun getUserId(): Int = intent.getIntExtra("userIdx", -1)
}