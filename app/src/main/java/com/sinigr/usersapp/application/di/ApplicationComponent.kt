package com.sinigr.usersapp.application.di

import android.content.Context
import com.bumptech.glide.RequestManager
import com.sinigr.usersapp.common.di.InjectionComponent
import com.sinigr.usersapp.modules.main.di.MainComponent
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    ApplicationModule::class,
    NetworkModule::class
  ]
)
interface ApplicationComponent : InjectionComponent {
  fun context(): Context
  fun networkManager(): CoroutineNetworkManager
  fun retrofitClient(): Retrofit
  fun glideRequestManager(): RequestManager

  fun createMainComponent(): MainComponent
}