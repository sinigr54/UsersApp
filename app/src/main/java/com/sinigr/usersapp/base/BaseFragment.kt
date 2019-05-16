package com.sinigr.usersapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), IView {

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