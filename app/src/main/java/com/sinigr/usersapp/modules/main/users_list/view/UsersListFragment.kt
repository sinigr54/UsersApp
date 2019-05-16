package com.sinigr.usersapp.modules.main.users_list.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sinigr.usersapp.R
import com.sinigr.usersapp.base.BaseFragment
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.modules.main.users_list.adapter.UsersAdapter
import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.presenter.IUsersListPresenter
import kotlinx.android.synthetic.main.fragment_list_users.*
import org.koin.android.ext.android.inject

class UsersListFragment : BaseFragment(), IUsersListView {

    companion object {
        private const val TAG = "UsersListFragment"
    }

    private val presenter: IUsersListPresenter by inject()

    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = UsersAdapter(activity!!) { user ->
            val action = UsersListFragmentDirections.actionUsersListFragmentToEditUserFragment(user.id!!)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        rv_users.adapter = adapter
        rv_users.layoutManager = LinearLayoutManager(activity)

        presenter.loadUsers()
    }

    override fun onUsersLoaded(users: List<UserEntity>) {
        adapter.addAll(users)
    }

    override fun onError(message: String) {

    }

    override fun onDestroyView() {
        presenter.detachView()

        super.onDestroyView()
    }
}