package com.example.news


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val editWeb: WebView = findViewById(R.id.web)
        val intent: Intent = intent

        val url: String? = intent.getStringExtra("url")
        editWeb.loadUrl(url ?: "https://google.com")

        val bar: ActionBar? = supportActionBar
        bar?.title = "News"
        bar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("blue")))

        bar?.setDisplayHomeAsUpEnabled(true)
        bar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}