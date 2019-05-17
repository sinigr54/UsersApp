package com.sinigr.usersapp.network.ioc

import com.sinigr.usersapp.network.RestServiceFactory
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.services.IUsersNetworkService
import org.koin.dsl.module

val networkModule = module {

    single { RestServiceFactory() }

    single { CoroutineNetworkManager() }

    single { get<RestServiceFactory>().createService(IUsersNetworkService::class.java) }

}