package com.sinigr.usersapp.modules.main.di

import android.content.Context
import com.sinigr.usersapp.model.users.IUsersRepository
import com.sinigr.usersapp.model.users.InMemoryUsersRepository
import com.sinigr.usersapp.network.errors.ErrorFactory
import com.sinigr.usersapp.network.services.IUsersNetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

  @MainScope
  @Provides
  fun provideUsersRepository(): IUsersRepository {
    return InMemoryUsersRepository()
  }

  @Provides
  fun provideUsersNetworkService(retrofit: Retrofit): IUsersNetworkService {
    return retrofit.create(IUsersNetworkService::class.java)
  }

  @Provides
  fun provideErrorFactory(context: Context): ErrorFactory {
    return ErrorFactory(context)
  }
}