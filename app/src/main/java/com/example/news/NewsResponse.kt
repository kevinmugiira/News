package com.example.news

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("status") val status: String,
    @SerializedName("articles") val articles: List<Article>
)

data class Article(
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("url") val url: String?,
    val id: String
)