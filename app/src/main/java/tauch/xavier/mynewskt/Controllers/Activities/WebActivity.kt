package tauch.xavier.mynewskt.Controllers.Activities


import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import android.view.MenuItem

import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

import tauch.xavier.mynewskt.R

class WebActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)


        // Add back button
        setSupportActionBar(activity_web_toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Recover the previous url
        val url = intent.getStringExtra("openUrl")

        // Open WebView
        activity_web_webview.webViewClient = WebViewClient()
        activity_web_webview.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}