package com.sinigr.usersapp.modules.main.users_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinigr.usersapp.base.BaseFragment
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.presenter.IUsersListPresenter
import org.koin.android.ext.android.inject

class UsersListFragment : BaseFragment(), IUsersListView {

    companion object {
        fun newInstance(): UsersListFragment {
            return UsersListFragment()
        }
    }

    private val presenter: IUsersListPresenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        presenter.loadUsers()
    }

    override fun onUsersLoaded(users: List<UserEntity>) {

    }

    override fun onError(message: String) {

    }
}