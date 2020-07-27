package com.sinigr.usersapp.common.di

interface InjectionComponent

interface InjectionComponentFactory<C : InjectionComponent, D : InjectionComponent> {
  fun create(parentComponent: D): C
  fun create(): C
}

abstract class ParentComponentFactory<C : InjectionComponent, D : InjectionComponent> :
  InjectionComponentFactory<C, D> {

  override fun create(): C {
    throw IllegalStateException("Single creation not supported")
  }

  companion object {
    inline fun <C : InjectionComponent, D : InjectionComponent> create(crossinline construct: (D) -> C): InjectionComponentFactory<C, D> {
      return object : ParentComponentFactory<C, D>() {
        override fun create(parentComponent: D): C {
          return construct(parentComponent)
        }
      }
    }
  }
}

abstract class SingleComponentFactory<C : InjectionComponent> :
  InjectionComponentFactory<C, InjectionComponent> {

  override fun create(parentComponent: InjectionComponent): C {
    throw IllegalStateException("Parent creation not supported")
  }

  companion object {
    inline fun <C : InjectionComponent> create(crossinline construct: () -> C): InjectionComponentFactory<C, InjectionComponent> {
      return object : SingleComponentFactory<C>() {
        override fun create(): C {
          return construct()
        }
      }
    }
  }
}