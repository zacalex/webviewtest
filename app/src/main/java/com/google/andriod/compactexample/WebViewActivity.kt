package com.google.andriod.compactexample

import android.graphics.Bitmap
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_view.*

import android.content.Intent
import android.annotation.SuppressLint
import android.view.View
import im.delight.android.webview.AdvancedWebView

class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        webview.settings.javaScriptEnabled = true

        val url = intent.getStringExtra("url")

        webview.loadUrl(url)
    }

}