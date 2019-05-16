package com.sinigr.usersapp.application

import android.app.Application
import com.sinigr.usersapp.modules.main.ioc.mainModule
import com.sinigr.usersapp.network.RestCore
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        RestCore.init()

        startKoin {
            androidContext(this@UsersApplication)
            modules(mainModule)
        }
    }

}