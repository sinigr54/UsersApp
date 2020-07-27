package com.sinigr.usersapp.common.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.sinigr.usersapp.common.di.InjectionAware
import com.sinigr.usersapp.common.di.InjectionComponent
import com.sinigr.usersapp.common.di.InjectionComponentFactory

abstract class InjectionFragment : Fragment(), InjectionAware {

  override var component: InjectionComponent? = null

  @Suppress("UNCHECKED_CAST")
  override fun onAttach(context: Context) {
    val factory = createComponentFactory() as? InjectionComponentFactory<*, InjectionComponent>
    if (factory != null) {
      component = createComponent(factory)
      onComponentCreated()
    }

    super.onAttach(context)
  }

  override fun createComponentFactory(): InjectionComponentFactory<*, *>? {
    return null
  }

  override fun createComponent(factory: InjectionComponentFactory<*, InjectionComponent>): InjectionComponent {
    val parentComponent: InjectionComponent? = parent()?.component
    return if (parentComponent != null) {
      factory.create(parentComponent)
    } else {
      factory.create()
    }
  }

  override fun onComponentCreated() {
    // nothing
  }

  override fun parent(): InjectionAware? {
    return if (parentContainer() == null) {
      activity as? InjectionAware
    } else {
      parentContainer() as? InjectionAware
    }
  }

  @Suppress("UNCHECKED_CAST")
  protected fun <T : InjectionComponent> component(): T? {
    return component as? T
  }
}