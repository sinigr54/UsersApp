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
import com.sinigr.usersapp.network.errors.ErrorFactory
import com.sinigr.usersapp.network.services.IUsersNetworkService
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {

    single<IUsersRepository> { InMemoryUsersRepository() }

    factory { get<Retrofit>().create(IUsersNetworkService::class.java) }
    factory { ErrorFactory(get()) }

    single<IEditUserInteractor> { EditUserInteractor(get(), get(), get(), get()) }
    single<IEditUserPresenter> { EditUserPresenter(get()) }

    single<IUsersListInteractor> { UsersListInteractor(get(), get(), get()) }
    single<IUsersListPresenter> { UsersListPresenter(get(), get()) }

}