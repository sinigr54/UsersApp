package com.sinigr.usersapp.modules.main.di

import com.sinigr.usersapp.common.di.InjectionComponent
import com.sinigr.usersapp.model.users.IUsersRepository
import com.sinigr.usersapp.modules.main.edit_users.di.EditUserComponent
import com.sinigr.usersapp.modules.main.edit_users.view.CreateUserFragment
import com.sinigr.usersapp.modules.main.edit_users.view.UpdateUserFragment
import com.sinigr.usersapp.modules.main.users_list.di.UsersListComponent
import com.sinigr.usersapp.modules.main.users_list.view.UsersListFragment
import com.sinigr.usersapp.network.errors.ErrorFactory
import com.sinigr.usersapp.network.services.IUsersNetworkService
import dagger.Subcomponent

@MainScope
@Subcomponent(
  modules = [
    MainModule::class
  ]
)
interface MainComponent : InjectionComponent {
  fun usersRepository(): IUsersRepository
  fun errorFactory(): ErrorFactory
  fun usersNetworkService(): IUsersNetworkService

  fun createUsersListComponent(): UsersListComponent
  fun createEditUserComponent(): EditUserComponent
}