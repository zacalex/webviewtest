package com.google.andriod.compactexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val netflix : Button = findViewById(R.id.netflix)
        netflix.setOnClickListener {
            startUrl("https://www.netflix.com")
        }

        amazon.setOnClickListener{
            startUrl("https://www.amazon.com")
        }

        hulu.setOnClickListener {
            startUrl("https://www.hulu.com")
        }

        ytb.setOnClickListener {
            startUrl("https://www.youtube.com")
        }

        cbs.setOnClickListener {
            startUrl("https://www.cbs.com")
        }

        abc.setOnClickListener {
            startUrl("https://www.abc.com")
        }
    }

    private fun startUrl(url: String) {
        val intent = Intent(this, WebViewActivity::class.java).apply {
            putExtra("url", url)
        }
        startActivity(intent)
    }
}
