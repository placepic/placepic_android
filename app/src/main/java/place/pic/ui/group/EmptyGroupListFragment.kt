package place.pic.ui.group

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_empty_group_list.*
import place.pic.R

class EmptyGroupListFragment : Fragment() {

    private var joinButtonClickListener:(()->Unit?)?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empty_group_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonMapping()
    }

    fun setJoinButtonClickListener(listener:()->Unit?){
        this.joinButtonClickListener = listener
    }

    private fun buttonMapping(){
        btn_join_group.setOnClickListener {
            Log.d("버튼 리스너","버튼 따란")
            joinButtonClickListener?.invoke()
        }
    }
}