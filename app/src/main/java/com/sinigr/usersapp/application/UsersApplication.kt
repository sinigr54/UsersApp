package com.sinigr.usersapp.application

import android.app.Application
import com.sinigr.usersapp.modules.main.ioc.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UsersApplication)
            modules(mainModule)
        }
    }

}