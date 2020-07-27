package com.sinigr.usersapp.modules.main.users_list.di

import com.sinigr.usersapp.model.users.IUsersRepository
import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.interactor.UsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.presenter.IUsersListPresenter
import com.sinigr.usersapp.modules.main.users_list.presenter.UsersListPresenter
import com.sinigr.usersapp.network.errors.ErrorFactory
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.services.IUsersNetworkService
import dagger.Module
import dagger.Provides

@Module
class UsersListModule {

  @Provides
  fun provideUsersInteractor(
    repository: IUsersRepository,
    networkService: IUsersNetworkService,
    networkManager: CoroutineNetworkManager,
    errorFactory: ErrorFactory
  ): IUsersListInteractor {
    return UsersListInteractor(repository, networkService, networkManager, errorFactory)
  }

  @Provides
  fun provideUsersPresenter(
    repository: IUsersRepository,
    interactor: IUsersListInteractor
  ): IUsersListPresenter {
    return UsersListPresenter(interactor, repository)
  }
}