package com.google.andriod.compactexample

import android.graphics.Bitmap
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_view.*

import android.content.Intent
import android.annotation.SuppressLint
import android.view.View
import im.delight.android.webview.AdvancedWebView

class WebViewActivity : AppCompatActivity(), AdvancedWebView.Listener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)


        val url = intent.getStringExtra("url")
        webview.setListener(this, this)
        webview.setDesktopMode(true)
        webview.setGeolocationEnabled(false)
        webview.setMixedContentAllowed(true)
        webview.setCookiesEnabled(true)
        webview.setThirdPartyCookiesEnabled(true)
        webview.addHttpHeader("X-Requested-With", "");

        webview.loadUrl(url)
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        webview.onResume()
        // ...
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        webview.onPause()
        // ...
        super.onPause()
    }

    override fun onDestroy() {
        webview.onDestroy()
        // ...
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        webview.onActivityResult(requestCode, resultCode, intent)
        // ...
    }

    override fun onBackPressed() {
        if (!webview.onBackPressed()) {
            return
        }
        // ...
        super.onBackPressed()
    }


    override fun onPageError(errorCode: Int, description: String, failingUrl: String) {

    }

    override fun onDownloadRequested(
        url: String,
        suggestedFilename: String,
        mimeType: String,
        contentLength: Long,
        contentDisposition: String,
        userAgent: String
    ) {


    }

    override fun onExternalPageRequest(url: String) {

    }

    override fun onPageFinished(url: String?) {
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        webview.visibility = View.VISIBLE
    }
}