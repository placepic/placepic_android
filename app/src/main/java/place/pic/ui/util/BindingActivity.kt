package place.pic.ui.util

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<T : ViewDataBinding>(
    @LayoutRes private val contentLayoutId: Int
) : AppCompatActivity() {
    private var nullableBinding: T? = null
    protected val binding:T
        get() = nullableBinding ?: error("ViewDataBinding object is not init")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nullableBinding = DataBindingUtil.setContentView(this, contentLayoutId)
    }
}
