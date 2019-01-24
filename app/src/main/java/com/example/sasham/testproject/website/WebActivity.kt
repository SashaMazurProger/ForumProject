package com.example.sasham.testproject.website

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sasham.testproject.base.BaseActivity
import com.example.sasham.testproject.R
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
        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.GONE
            }
        }

        webView.loadUrl(url)
        title = url
    }

    companion object {

        val WEB_URL_ARGS = "url"


        fun startActivity(url: String, context: Context) {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra(WebActivity.WEB_URL_ARGS, url)
            context.startActivity(intent)
        }
    }
}
