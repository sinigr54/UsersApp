package com.sinigr.usersapp.modules.main.users_list.di

import com.sinigr.usersapp.common.di.InjectionComponent
import com.sinigr.usersapp.modules.main.users_list.view.UsersListFragment
import dagger.Subcomponent

@Subcomponent(
  modules = [
    UsersListModule::class
  ]
)
interface UsersListComponent : InjectionComponent {
  fun inject(fragment: UsersListFragment)
}