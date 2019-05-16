package com.sinigr.usersapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseFragment : Fragment(), IView {

    fun setSupportActionbar(title: Int, isShowBackArrow: Boolean = false) {
        val textTitle = resources.getString(title)

        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(toolbar)

        val actionBar = appCompatActivity?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(isShowBackArrow)
        actionBar?.setDisplayShowHomeEnabled(isShowBackArrow)

        actionBar?.title = textTitle
    }

    override fun showLoadingDialog() {
        // Nothing
    }

    override fun dismissLoadingDialog() {
        // Nothing
    }
}