package com.sinigr.usersapp.application.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sinigr.usersapp.BuildConfig
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Singleton
  @Provides
  fun provideNetworkManager(): CoroutineNetworkManager {
    return CoroutineNetworkManager()
  }

  @Provides
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Provides
  fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
      builder.addInterceptor(httpLoggingInterceptor)
    }

    return builder.build()
  }

  @Provides
  fun provideCallAdapterFactory(): CallAdapter.Factory {
    return CoroutineCallAdapterFactory()
  }

  @Provides
  fun provideGson(): Gson {
    return GsonBuilder()
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create()
  }

  @Provides
  fun provideConverterFactory(gson: Gson): Converter.Factory {
    return GsonConverterFactory.create(gson)
  }

  @Singleton
  @Provides
  fun provideRetrofitClient(
    okHttpClient: OkHttpClient,
    converterFactory: Converter.Factory,
    callAdapterFactory: CallAdapter.Factory
  ): Retrofit {

    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(converterFactory)
      .addCallAdapterFactory(callAdapterFactory)
      .build()
  }

  @Singleton
  @Provides
  fun provideGlideRequestManager(context: Context): RequestManager {
    return Glide.with(context)
  }
}