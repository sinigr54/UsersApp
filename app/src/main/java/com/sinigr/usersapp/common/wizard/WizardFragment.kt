package com.sinigr.usersapp.common.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.findNavController
import com.sinigr.usersapp.R
import com.sinigr.usersapp.common.fragment.BaseFragment

abstract class WizardFragment : BaseFragment() {

  protected lateinit var contentNavigationController: NavController

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(wizardLayout(), container, false)

    contentNavigationController = view.findViewById<View>(containerId()).findNavController()
    contentNavigationController.graph = inflateGraph(contentNavigationController.navInflater)

    return view
  }

  open fun wizardLayout(): Int {
    return R.layout.fragment_wizard_container
  }

  open fun containerId(): Int {
    return R.id.content_container
  }

  abstract fun defaultGraph(): Int

  open fun startDestination(): Int? {
    return null
  }

  private fun inflateGraph(inflater: NavInflater): NavGraph {
    val graph = inflater.inflate(defaultGraph())
    startDestination()?.let { startDestination ->
      graph.startDestination = startDestination
    }
    return graph
  }
}