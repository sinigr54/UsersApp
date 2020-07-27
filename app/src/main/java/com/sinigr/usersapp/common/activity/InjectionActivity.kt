package com.sinigr.usersapp.common.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sinigr.usersapp.common.di.InjectionAware
import com.sinigr.usersapp.common.di.InjectionComponent
import com.sinigr.usersapp.common.di.InjectionComponentFactory

abstract class InjectionActivity : AppCompatActivity(), InjectionAware {

  override var component: InjectionComponent? = null

  @Suppress("UNCHECKED_CAST")
  override fun onCreate(savedInstanceState: Bundle?) {
    val factory = createComponentFactory() as? InjectionComponentFactory<*, InjectionComponent>
    if (factory != null) {
      component = createComponent(factory)
      onComponentCreated()
    }

    super.onCreate(savedInstanceState)
  }
}