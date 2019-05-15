package com.sinigr.usersapp.modules.main.ioc

import com.sinigr.usersapp.data.users.InMemoryUsersRepository
import com.sinigr.usersapp.data.users.UsersRepository
import com.sinigr.usersapp.modules.main.edit_users.interactor.EditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.presenter.EditUserPresenter
import com.sinigr.usersapp.modules.main.edit_users.presenter.IEditUserPresenter
import com.sinigr.usersapp.modules.main.edit_users.router.EditUsersRouter
import com.sinigr.usersapp.modules.main.edit_users.router.IEditUsersRouter
import com.sinigr.usersapp.modules.main.users_list.interactor.UsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.router.UsersListRouter
import com.sinigr.usersapp.modules.main.users_list.router.IUsersListRouter
import org.koin.dsl.module

val mainModule = module {

    single<UsersRepository> { InMemoryUsersRepository() }

    single<IEditUserInteractor> { EditUserInteractor(get()) }
    single<IEditUsersRouter> { EditUsersRouter() }
    single<IEditUserPresenter> { EditUserPresenter(get(), get())}

    single<IUsersListInteractor> { UsersListInteractor()}
    single<IUsersListRouter> { UsersListRouter() }

}