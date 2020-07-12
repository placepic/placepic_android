package place.pic.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_write.view.*
import place.pic.R
import place.pic.ui.login.LoginActivity
import place.pic.ui.main.place.Place
import place.pic.ui.search.place.PlaceSearchActivity
import place.pic.ui.signup.SignupSecondActivity


class WriteFragment : BottomSheetDialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_write, container, false)
        val intent = Intent(context, PlaceSearchActivity::class.java)
        //handle clicks
        v.iv_restaurant.setOnClickListener {
            intent.putExtra("groupIdx",Place.Type.RESTAURANT)
            select(intent)
        }
        v.iv_drink.setOnClickListener {
            intent.putExtra("groupIdx",Place.Type.ALCOHOL)
            select(intent)
        }
        v.iv_cafe.setOnClickListener {
            intent.putExtra("groupIdx",Place.Type.CAFE)
            select(intent)
        }
        v.iv_study.setOnClickListener {
            intent.putExtra("groupIdx",Place.Type.STUDY)
            select(intent)
        }
        v.iv_etc.setOnClickListener {
            intent.putExtra("groupIdx",Place.Type.ETC)
            select(intent)
        }
        return v
    }
    interface BottomSheetListener{
        fun onOptionClick(text: String)
    }
    fun select(intent: Intent)
    {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}