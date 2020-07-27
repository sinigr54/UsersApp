package com.sinigr.usersapp.application.di

import android.content.Context

object ApplicationComponentManager {
  lateinit var component: ApplicationComponent
    private set

  fun initialize(context: Context) {
    component = DaggerApplicationComponent.builder()
      .applicationModule(ApplicationModule(context))
      .networkModule(NetworkModule())
      .build()
  }
}