package com.example.lingosa.data.network

//package com.example.lingosa.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit client configuration
 * Singleton object that provides API service instance
 */
object ApiClient {

    private const val TAG = "ApiClient"

    // TODO: Replace with your actual backend URL
    private const val BASE_URL = "https://your-backend-url.com/api/"

    /**
     * Logging interceptor for debugging network calls
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * OkHttp client with interceptors and timeouts
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    /**
     * Retrofit instance with base URL and converters
     */
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * API service instance
     */
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}