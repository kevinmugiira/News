package com.example.news

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context

class NewsActivity : AppCompatActivity() {

//    private val newsList = ArrayList<Article>()
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: NewsAdapter

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_news)
//        recyclerView = findViewById(R.id.recyclerView)
//
//        recyclerView.layoutManager = LinearLayoutManager(this@NewsActivity)
//        adapter = NewsAdapter(newsList, R.layout.item_news)
//        recyclerView.adapter = adapter
//        fetchData(this) {news ->
//            newsList.addAll(news.articles)
//            adapter.notifyDataSetChanged()
//        }
//
//
//    }

//    private fun fetchData(context: Context, callback: (NewsResponse) -> Unit) {
//        val apiKey = "a4a02da3e52e4ca887761273351e9ebc"
//        val service = ApiClient.newsApiService
//
//        service.getNews("tesla", "2023-10-25", "publishedAt", apiKey)
//            .enqueue(object : Callback<NewsResponse> {
//                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
//                    if (response.isSuccessful) {
//                        val news: NewsResponse? = response.body()
//                        news?.let { callback(it)
////                        val news: NewsResponse = response.body() as NewsResponse
////                        callback(news)
////                        response.body()?.articles?.let {
////                            newsList.addAll(it)
////                            adapter.notifyDataSetChanged()
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                    Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
//                }
//            })
//    }
}