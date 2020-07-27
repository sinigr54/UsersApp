package com.sinigr.usersapp.common.activity

import com.sinigr.usersapp.application.di.ApplicationComponentManager
import com.sinigr.usersapp.common.di.InjectionAware
import com.sinigr.usersapp.common.di.InjectionComponent
import com.sinigr.usersapp.common.di.InjectionComponentFactory
import com.sinigr.usersapp.common.di.SingleComponentFactory

abstract class BaseActivity : InjectionActivity() {
  override fun createComponentFactory(): InjectionComponentFactory<*, *>? {
    return SingleComponentFactory.create {
      ApplicationComponentManager.component
    }
  }

  override fun createComponent(factory: InjectionComponentFactory<*, InjectionComponent>): InjectionComponent {
    return factory.create()
  }

  override fun onComponentCreated() {
    // nothing
  }

  override fun parent(): InjectionAware? {
    return null
  }
}