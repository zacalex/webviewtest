package com.google.andriod.compactexample

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_view.*
import java.time.Duration

class WebViewActivity : AppCompatActivity(), OnGestureListener {

    private var gestureDetector = GestureDetector(this)
    override fun onShowPress(p0: MotionEvent?) {
        Toast.makeText(this, "on show press", Toast.LENGTH_SHORT)
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        Toast.makeText(this, "on single tap up", Toast.LENGTH_SHORT)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        Toast.makeText(this, "on down", Toast.LENGTH_SHORT)
        return true

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        Toast.makeText(this, "on Fling", Toast.LENGTH_SHORT)
        var res = false
        var diffY = 0f
        var diffX = 0f

        if (p0 != null || p1 != null) {
            diffY = p0!!.y - p1!!.y
            diffX = p0!!.x - p1!!.x
        }

        if(Math.abs(diffX) > 100 && Math.abs(p2) > 100) {
            if(diffX > 0) onSwipeLeft()
            else onSwipeRight()
        }

        return res
    }

    private fun onSwipeRight() {
        Toast.makeText(this, "onSwipeRight", Toast.LENGTH_SHORT)
    }

    private fun onSwipeLeft() {
        Toast.makeText(this, "onSwipeLeft", Toast.LENGTH_SHORT)
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        Toast.makeText(this, "on scroll", Toast.LENGTH_SHORT)
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
        Toast.makeText(this, "on long press", Toast.LENGTH_SHORT)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        webview.settings.javaScriptEnabled = true

        val url = intent.getStringExtra("url")
        val newUA =
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36"
        val settings = webview.settings
        settings.setJavaScriptEnabled(true)

        // configure local storage apis and their database paths.
        settings.setAppCachePath(getDir("appcache", 0).path)
        settings.setGeolocationDatabasePath(getDir("geolocation", 0).path)
        settings.setDatabasePath(getDir("databases", 0).path)
        settings.setUserAgentString(newUA)
        settings.setAppCacheEnabled(true)
        settings.setGeolocationEnabled(true)
        settings.setDatabaseEnabled(true)
        settings.setDomStorageEnabled(true)

        webview.loadUrl(url)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        Log.d("compact", "touchevent")
        return super.onTouchEvent(event)
    }

}