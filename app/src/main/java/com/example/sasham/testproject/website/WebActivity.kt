package com.example.sasham.testproject.website

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        openWebSite()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun openWebSite() {
        val url = intent.getStringExtra(WEB_URL_ARGS)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
        title = url
    }

    companion object {

        const val WEB_URL_ARGS = "url"


        fun startActivity(url: String, context: Context) {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra(WebActivity.WEB_URL_ARGS, url)
            context.startActivity(intent)
        }
    }
}
