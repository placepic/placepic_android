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
import place.pic.data.entity.Place
import place.pic.ui.login.LoginActivity
import place.pic.ui.search.place.PlaceSearchActivity



class WriteFragment : BottomSheetDialogFragment(){

    override fun getTheme(): Int {
        return R.style.Widget_AppTheme_BottomSheet
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_write, container, false)
        val intent = Intent(context, PlaceSearchActivity::class.java)
        //handle clicks
        v.iv_restaurant.setOnClickListener {
            intent.putExtra("categoryIdx", Place.Type.RESTAURANT)
            select(intent)
            dismiss()
        }
        v.iv_drink.setOnClickListener {
            intent.putExtra("categoryIdx",Place.Type.ALCOHOL)
            select(intent)
            dismiss()
        }
        v.iv_cafe.setOnClickListener {
            intent.putExtra("categoryIdx",Place.Type.CAFE)
            select(intent)
            dismiss()
        }
        v.iv_study.setOnClickListener {
            intent.putExtra("categoryIdx",Place.Type.STUDY)
            select(intent)
            dismiss()
        }
        v.iv_etc.setOnClickListener {
            intent.putExtra("categoryIdx",Place.Type.ETC)
            select(intent)
            dismiss()
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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