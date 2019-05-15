package com.sinigr.usersapp.modules.main.users_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinigr.usersapp.base.BaseFragment
import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import org.koin.android.ext.android.inject

class UsersListFragment : BaseFragment() {

    companion object {
        fun newInstance(): UsersListFragment {
            return UsersListFragment()
        }
    }

    val interactor: IUsersListInteractor by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}