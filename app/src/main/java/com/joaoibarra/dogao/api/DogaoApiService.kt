package com.joaoibarra.dogao.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.joaoibarra.dogao.Constants
import com.joaoibarra.gitapp.api.DogaoApi
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DogaoApiService {
    fun create(): DogaoApi{
        val builder = OkHttpClient.Builder()
        val gsonBuilder = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()

        // Logging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(interceptor)

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create(gsonBuilder))
                .baseUrl(Constants.API)
                .client(builder.build())
                .build()
        return retrofit.create(DogaoApi::class.java)
    }

    private fun getHeader(): Headers {
        val builder = Headers.Builder()
        builder.add("Accept", "application/vnd.github.v3.text-match+json")
        return builder.build()
    }
}