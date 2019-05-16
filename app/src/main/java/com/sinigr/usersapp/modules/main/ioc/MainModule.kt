package com.sinigr.usersapp.modules.main.ioc

import com.sinigr.usersapp.data.users.InMemoryUsersRepository
import com.sinigr.usersapp.data.users.IUsersRepository
import com.sinigr.usersapp.modules.main.edit_users.interactor.EditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.presenter.EditUserPresenter
import com.sinigr.usersapp.modules.main.edit_users.presenter.IEditUserPresenter
import com.sinigr.usersapp.modules.main.users_list.interactor.UsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.presenter.IUsersListPresenter
import com.sinigr.usersapp.modules.main.users_list.presenter.UsersListPresenter
import com.sinigr.usersapp.network.RestServiceFactory
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.services.IUsersNetworkService
import org.koin.dsl.module

val mainModule = module {

    single<IUsersRepository> { InMemoryUsersRepository() }

    single { RestServiceFactory.createService(IUsersNetworkService::class.java) }

    single { CoroutineNetworkManager() }

    single<IEditUserInteractor> { EditUserInteractor(get(), get(), get()) }
    single<IEditUserPresenter> { EditUserPresenter(get()) }

    single<IUsersListInteractor> { UsersListInteractor(get(), get(), get()) }
    single<IUsersListPresenter> { UsersListPresenter(get()) }

}