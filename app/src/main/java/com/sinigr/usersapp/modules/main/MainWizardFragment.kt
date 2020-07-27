package com.sinigr.usersapp.modules.main

import com.sinigr.usersapp.R
import com.sinigr.usersapp.application.di.ApplicationComponent
import com.sinigr.usersapp.common.di.InjectionComponentFactory
import com.sinigr.usersapp.common.di.ParentComponentFactory
import com.sinigr.usersapp.common.wizard.WizardFragment

class MainWizardFragment : WizardFragment() {

  override fun createComponentFactory(): InjectionComponentFactory<*, *>? {
    return ParentComponentFactory.create { parent: ApplicationComponent ->
      parent.createMainComponent()
    }
  }

  override fun defaultGraph(): Int {
    return R.navigation.main_navigation_graph
  }
}