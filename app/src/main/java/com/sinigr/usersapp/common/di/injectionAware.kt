package com.sinigr.usersapp.common.di

interface InjectionAware {
  var component: InjectionComponent?
  fun createComponentFactory(): InjectionComponentFactory<*, *>?
  fun createComponent(factory: InjectionComponentFactory<*, InjectionComponent>): InjectionComponent
  fun onComponentCreated()
  fun parent(): InjectionAware?
}