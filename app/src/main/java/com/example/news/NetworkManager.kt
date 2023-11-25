package com.example.news

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient

object NetworkManager {
    private val certificatePinner = CertificatePinner.Builder()
        .add(
            "https://newsapi.org",
            "sha256/47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU="
        )
        .build()

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .certificatePinner(certificatePinner)
        .build()
}
