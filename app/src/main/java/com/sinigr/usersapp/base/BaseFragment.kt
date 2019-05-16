package com.sinigr.usersapp.base

import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment(), IView {

    override fun showLoadingDialog() {

    }

    override fun dismissLoadingDialog() {

    }
}