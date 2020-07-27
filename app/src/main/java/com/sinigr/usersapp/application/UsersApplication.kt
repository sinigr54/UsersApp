package com.sinigr.usersapp.application

import android.app.Application
import com.sinigr.usersapp.application.di.ApplicationComponentManager

class UsersApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    ApplicationComponentManager.initialize(this)
  }

}