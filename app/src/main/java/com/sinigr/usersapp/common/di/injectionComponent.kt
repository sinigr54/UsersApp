package com.sinigr.usersapp.common.di

interface InjectionComponent

interface InjectionComponentFactory<C : InjectionComponent, P : InjectionComponent> {
  fun create(parentComponent: P): C
  fun create(): C
}

abstract class SubcomponentFactory<C : InjectionComponent, P : InjectionComponent> :
  InjectionComponentFactory<C, P> {

  override fun create(): C {
    throw IllegalStateException("Single creation not supported")
  }

  companion object {
    inline fun <C : InjectionComponent, P : InjectionComponent> create(crossinline construct: (P) -> C): InjectionComponentFactory<C, P> {
      return object : SubcomponentFactory<C, P>() {
        override fun create(parentComponent: P): C {
          return construct(parentComponent)
        }
      }
    }
  }
}

abstract class ComponentFactory<C : InjectionComponent> :
  InjectionComponentFactory<C, InjectionComponent> {

  override fun create(parentComponent: InjectionComponent): C {
    throw IllegalStateException("Parent creation not supported")
  }

  companion object {
    inline fun <C : InjectionComponent> create(crossinline construct: () -> C): InjectionComponentFactory<C, InjectionComponent> {
      return object : ComponentFactory<C>() {
        override fun create(): C {
          return construct()
        }
      }
    }
  }
}