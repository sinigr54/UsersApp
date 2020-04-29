package com.sinigr.usersapp.network.ioc

import android.content.Context
import com.bumptech.glide.Glide
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sinigr.usersapp.BuildConfig
import com.sinigr.usersapp.network.Constants
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { CoroutineNetworkManager() }

    factory {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(get<HttpLoggingInterceptor>())
        }

        builder.build()
    }

    factory {
        CoroutineCallAdapterFactory()
    }

    factory {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    factory {
        GsonConverterFactory.create(get<Gson>())
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(getProperty(Constants.BASE_URL_KEY, ""))
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
            .build()
    }

    single {
        Glide.with(get<Context>())
    }

}