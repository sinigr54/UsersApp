package com.sinigr.usersapp.modules.main.edit_users.di

import com.sinigr.usersapp.common.di.InjectionComponent
import com.sinigr.usersapp.modules.main.edit_users.view.CreateUserFragment
import com.sinigr.usersapp.modules.main.edit_users.view.UpdateUserFragment
import dagger.Subcomponent

@Subcomponent(
  modules = [
    EditUserModule::class
  ]
)
interface EditUserComponent : InjectionComponent {
  fun inject(fragment: CreateUserFragment)
  fun inject(fragment: UpdateUserFragment)
}