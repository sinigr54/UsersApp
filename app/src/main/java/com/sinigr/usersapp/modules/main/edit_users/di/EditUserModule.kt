package com.sinigr.usersapp.modules.main.edit_users.di

import com.sinigr.usersapp.model.users.IUsersRepository
import com.sinigr.usersapp.modules.main.edit_users.interactor.EditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.presenter.EditUserPresenter
import com.sinigr.usersapp.modules.main.edit_users.presenter.IEditUserPresenter
import com.sinigr.usersapp.network.errors.ErrorFactory
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.services.IUsersNetworkService
import dagger.Module
import dagger.Provides

@Module
class EditUserModule {

  @Provides
  fun provideEditUserInteractor(
    repository: IUsersRepository,
    networkService: IUsersNetworkService,
    networkManager: CoroutineNetworkManager,
    errorFactory: ErrorFactory
  ): IEditUserInteractor {
    return EditUserInteractor(repository, networkService, networkManager, errorFactory)
  }

  @Provides
  fun provideEditUserPresenter(interactor: IEditUserInteractor): IEditUserPresenter {
    return EditUserPresenter(interactor)
  }
}