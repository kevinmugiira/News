package com.example.news
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.FragmentNewsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

//import okhttp3.Call
//import okhttp3.Callback
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.Response
//import java.io.IOException

class NewsFragment : Fragment(), NewsAdapter.OnItemClickListener {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val newsList = ArrayList<Article>()
    private lateinit var adapter: NewsAdapter

    companion object {
        private const val ARG_NEWS_SOURCE = "news_source"

        // Create a new instance of NewsFragment and pass the news source as an argument
        fun newInstance(newsSource: String): NewsFragment {
            val fragment = NewsFragment()
            val args = Bundle()
            args.putString(ARG_NEWS_SOURCE, newsSource)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        val newsSource = arguments?.getString(ARG_NEWS_SOURCE)

        newsSource?.let { source ->
            fetchData(requireContext(), source) { news ->
                newsList.addAll(news.articles)
                adapter.notifyDataSetChanged()
            }
        } ?: run {
            // Handle the case where newsSource is null
            Toast.makeText(requireContext(), "Invalid news source", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onItemClick(article: Article) {
        // Handle item click
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("article_id", article.id) // Pass any necessary data to show news details
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val clickListener = object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(article: Article) {
                // Handle item click here
                val intent = Intent(requireContext(), DetailsActivity::class.java)
                intent.putExtra("url", article.url)
                startActivity(intent)
            }
        }

        adapter = NewsAdapter(newsList, clickListener)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

//    fun fetchData(context: Context, newsSource: String?, callback: (NewsResponse) -> Unit) {
//        newsSource?.let { source ->
//            val okHttpClient = NetworkManager.okHttpClient // Using the OkHttpClient instance from NetworkManager
//
//            val request = Request.Builder()
//                .url("https://newsapi.org/v2/everything?q=USA%20Today&from=2023-10-25&sortBy=publishedAt&apiKey=a4a02da3e52e4ca887761273351e9ebc")
//                .build()
//
//            okHttpClient.newCall(request).enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    // Display a Toast for the failure on the main UI thread
//                    (context as? TabActivity)?.runOnUiThread {
//                        Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    if (response.isSuccessful) {
//                        val responseData = response.body?.string()
//
//                        // Parse the JSON response into NewsResponse using Gson
//                        val news: NewsResponse? = Gson().fromJson(responseData, object : TypeToken<NewsResponse>() {}.type)
//
//                        // Check if the news object is not null and not empty
//                        news?.let {
//                            if (it.articles.isNotEmpty()) {
//                                // Invoke the callback with the parsed news data on the main UI thread
//                                (context as? TabActivity)?.runOnUiThread {
//                                    callback(it)
//                                }
//                            } else {
//                                // Handle empty news data on the main UI thread
//                                (context as? TabActivity)?.runOnUiThread {
//                                    Toast.makeText(context, "No news articles found", Toast.LENGTH_SHORT).show()
//                                }
//                            }
//                        } ?: run {
//                            // Handle null news data on the main UI thread
//                            (context as? TabActivity)?.runOnUiThread {
//                                Toast.makeText(context, "Failed to parse news data", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    } else {
//                        // Handle unsuccessful response on the main UI thread
//                        (context as? TabActivity)?.runOnUiThread {
//                            Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            })
//        } ?: run {
//            // Handle invalid news source on the main UI thread
//            (context as? TabActivity)?.runOnUiThread {
//                Toast.makeText(context, "Invalid news source", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

//    fun fetchData(context: Context, newsSource: String?, callback: (NewsResponse) -> Unit) {
//        newsSource?.let { source ->
//            val okHttpClient = NetworkManager.okHttpClient // Using the OkHttpClient instance from NetworkManager
//
//            val request = Request.Builder()
//                .url("https://your-api-url.com/news?source=$source&from=2023-10-25&sortBy=publishedAt&apiKey=your_api_key")
//                .build()
//
//            okHttpClient.newCall(request).enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    if (response.isSuccessful) {
//                        val responseData = response.body?.string()
//
//                        // Parse the JSON response into NewsResponse using Gson
//                        val news: NewsResponse? = Gson().fromJson(responseData, object : TypeToken<NewsResponse>() {}.type)
//
//                        // Check if the news object is not null and not empty
//                        news?.let {
//                            if (it.articles.isNotEmpty()) {
//                                callback(it) // Invoke the callback with the parsed news data
//
////                            val news: NewsResponse? = response.body()
////                        callback(news)
//                    } else {
//                        // Handle unsuccessful response
//                        Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
////                override fun onFailure(call: Call, t: Throwable) {
////                    // Handle failure or exceptions here
////                    Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show()
////                }
//            })
//        } ?: run {
//            Toast.makeText(context, "Invalid news source", Toast.LENGTH_SHORT).show()
//        }
//    }

    fun fetchData(context: Context, newsSource: String?, callback: (NewsResponse) -> Unit) {
        newsSource?.let { source ->
            val apiKey = "a4a02da3e52e4ca887761273351e9ebc"
            val service = ApiClient.newsApiService

            service.getNews(source, "2023-10-25", "publishedAt", apiKey)
                .enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                        if (response.isSuccessful) {
                            val news: NewsResponse? = response.body()
                            news?.let { callback(it) }
                        } else {
                            Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show()
                    }
                })
        } ?: run {
            Toast.makeText(context, "Invalid news source", Toast.LENGTH_SHORT).show()
        }
    }
}