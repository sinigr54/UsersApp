package com.sinigr.usersapp.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestServiceFactory {
    companion object {
        private const val BASE_URL = "https://frogogo-test.herokuapp.com"
    }

    private var retrofit: Retrofit? = null

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .build()
    }

    fun <T> createService(serviceClass: Class<T>): T {
        if (retrofit == null) {
            throw IllegalStateException("Call `RestCore.init()` before calling this method.")
        }

        return retrofit!!.create(serviceClass)
    }
}