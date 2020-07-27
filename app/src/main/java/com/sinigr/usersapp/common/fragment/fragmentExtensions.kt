package com.sinigr.usersapp.common.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

fun Fragment.parentContainer(): Fragment? {
  var parent: Fragment? = parentFragment ?: return null

  if (parent is NavHostFragment) {
    parent = parent.parentContainer()
  }

  return parent
}