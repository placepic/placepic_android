package place.pic.ui.main

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import place.pic.R
import place.pic.data.entity.Place
import place.pic.ui.group.waitgrouplist.WaitGroupActivity
import place.pic.ui.search.place.PlaceSearchActivity


class MyPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_my_page, container, false)
        v.cl_user_list.setOnClickListener {
            val intent = Intent(context, WaitGroupActivity::class.java)
            startActivity(intent)
        }
        return v
    }
}