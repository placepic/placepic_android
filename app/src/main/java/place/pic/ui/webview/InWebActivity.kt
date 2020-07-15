package place.pic.ui.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import place.pic.R
import place.pic.databinding.ActivityWebBinding
import java.lang.IllegalArgumentException

/**
 * Created By Malibin
 * on 7월 14, 2019
 */

class InWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
            return
        }
        super.onBackPressed()
    }

    private fun initView() {
        setProgressbarColor()
        binding.webView.apply {
            webViewClient = MWebViewClient(binding)
            webChromeClient = MWebChromeClient(binding)
            applyWebViewSettings()
            loadUrl(getWebUrl())
        }
        binding.btnClose.setOnClickListener { finish() }
        binding.title.text = getWebTitle()
    }

    private fun setProgressbarColor() {
        val color = ContextCompat.getColor(this, R.color.colorPrimary)
        binding.progressbar.progressDrawable.setColorFilter(
            color, android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    private fun applyWebViewSettings() {
        binding.webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(false)
            builtInZoomControls = false
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            cacheMode = WebSettings.LOAD_NO_CACHE
            domStorageEnabled = true
        }
    }

    private fun getWebUrl() = intent.getStringExtra("webUrl")
        ?: throw IllegalArgumentException("webUrl must be sended")

    private fun getWebTitle() = intent.getStringExtra("webTitle")
        ?: throw IllegalArgumentException("webTitle must be sended")


    private class MWebViewClient(private val binding: ActivityWebBinding) : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            binding.progressbar.visibility = View.VISIBLE
        }
    }

    private class MWebChromeClient(private val binding: ActivityWebBinding) : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            binding.progressbar.progress = newProgress
            if (newProgress == 100) {
                binding.progressbar.visibility = View.GONE
            }
        }
    }
}
