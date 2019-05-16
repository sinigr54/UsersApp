package com.sinigr.usersapp.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), IView {

    override fun showLoadingDialog() {

    }

    override fun dismissLoadingDialog() {

    }
}