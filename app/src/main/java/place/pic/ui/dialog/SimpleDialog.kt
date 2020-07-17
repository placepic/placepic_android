package place.pic.ui.dialog

/**
 * Created By Malibin
 * on 7월 17, 2020
 */

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import place.pic.R
import place.pic.databinding.DialogSimpleTwoButtonBinding

open class SimpleDialog(context: Context) : AlertDialog(context), SimpleDialogOnClickListener {

    private var content = ""
    private var okButtonText = "확인"
    private var cancelButtonText = "취소"

    private var cancelClickListener: ((view: View) -> Unit)? = { dismiss() }
    private var okClickListener: ((view: View) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DialogSimpleTwoButtonBinding.inflate(layoutInflater).apply {
            clickListener = this@SimpleDialog
            content.text = this@SimpleDialog.content
            btnOk.text = this@SimpleDialog.okButtonText
            btnCancel.text = this@SimpleDialog.cancelButtonText
        }
        setContentView(binding.root)
        setTransparentWindowBackground()
    }

    private fun setTransparentWindowBackground() {
        window?.setBackgroundDrawableResource(R.color.transparent)
    }

    override fun onCancelClick(view: View) {
        cancelClickListener?.invoke(view)
    }

    override fun onOkClick(view: View) {
        okClickListener?.invoke(view)
    }

    fun setContent(content: String) {
        this.content = content
    }

    fun setContent(@StringRes content: Int) {
        this.content = context.resources.getString(content)
    }

    fun setSimpleDialogOnClickListener(listener: SimpleDialogOnClickListener) {
        cancelClickListener = listener::onCancelClick
        okClickListener = listener::onOkClick
    }

    fun setOkClickListener(
        okButtonText: String = "확인",
        listener: ((view: View) -> Unit)?
    ) {
        this.okButtonText = okButtonText
        this.okClickListener = listener
    }

    fun setOkClickListener(
        @StringRes okButtonText: Int = R.string.ok,
        listener: ((view: View) -> Unit)?
    ) {
        this.okButtonText = context.resources.getString(okButtonText)
        this.okClickListener = listener
    }

    fun setCancelClickListener(
        cancelButtonText: String = "취소",
        listener: ((view: View) -> Unit)?
    ) {
        this.cancelButtonText = cancelButtonText
        this.cancelClickListener = listener
    }

    fun setCancelClickListener(
        @StringRes cancelButtonText: Int = R.string.cancel,
        listener: ((view: View) -> Unit)?
    ) {
        this.cancelButtonText = context.resources.getString(cancelButtonText)
        this.cancelClickListener = listener
    }
}
