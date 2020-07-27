package com.sinigr.usersapp.common.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sinigr.usersapp.common.IView

abstract class BaseFragment : InjectionFragment(), IView {

  fun setSupportActionbar(title: String, isShowBackArrow: Boolean = false) {
    val appCompatActivity = (requireActivity() as? AppCompatActivity)

    val actionBar = appCompatActivity?.supportActionBar
    actionBar?.setDisplayHomeAsUpEnabled(isShowBackArrow)
    actionBar?.setDisplayShowHomeEnabled(isShowBackArrow)

    actionBar?.title = title
  }

  fun setSupportActionbar(title: Int, isShowBackArrow: Boolean = false) {
    val textTitle = resources.getString(title)

    setSupportActionbar(textTitle, isShowBackArrow)
  }

  override fun showLoadingDialog() {
    // Nothing
  }

  override fun dismissLoadingDialog() {
    // Nothing
  }
}