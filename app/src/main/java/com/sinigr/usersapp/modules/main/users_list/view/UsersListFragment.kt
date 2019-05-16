package com.sinigr.usersapp.modules.main.users_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sinigr.usersapp.R
import com.sinigr.usersapp.base.BaseFragment
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.presenter.IUsersListPresenter
import kotlinx.android.synthetic.main.fragment_list_users.*
import org.koin.android.ext.android.inject

class UsersListFragment : BaseFragment(), IUsersListView {

    private val presenter: IUsersListPresenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_usersListFragment_to_editUserFragment)
        }
    }

    override fun onUsersLoaded(users: List<UserEntity>) {

    }

    override fun onError(message: String) {

    }
}