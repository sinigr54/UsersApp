package com.sinigr.usersapp.application

import android.app.Application
import com.sinigr.usersapp.modules.main.ioc.mainModule
import com.sinigr.usersapp.network.RestServiceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApplication : Application() {

    companion object {
        lateinit var instance: UsersApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        RestServiceFactory.init()

        startKoin {
            androidContext(this@UsersApplication)
            modules(mainModule)
        }
    }

}