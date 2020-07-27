package com.sinigr.usersapp.modules.main.users_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.sinigr.usersapp.R
import com.sinigr.usersapp.common.di.InjectionComponent
import com.sinigr.usersapp.common.di.InjectionComponentFactory
import com.sinigr.usersapp.common.di.ParentComponentFactory
import com.sinigr.usersapp.common.fragment.BaseFragment
import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.modules.main.di.MainComponent
import com.sinigr.usersapp.modules.main.users_list.adapter.UsersAdapter
import com.sinigr.usersapp.modules.main.users_list.di.UsersListComponent
import com.sinigr.usersapp.modules.main.users_list.presenter.IUsersListPresenter
import kotlinx.android.synthetic.main.fragment_list_users.*
import javax.inject.Inject

class UsersListFragment : BaseFragment(), IUsersListView, SwipeRefreshLayout.OnRefreshListener {

  @Inject
  lateinit var presenter: IUsersListPresenter

  private lateinit var adapter: UsersAdapter

  override fun createComponentFactory(): InjectionComponentFactory<*, *>? {
    return ParentComponentFactory.create { parent: MainComponent ->
      parent.createUsersListComponent()
    }
  }

  override fun onComponentCreated() {
    component<UsersListComponent>()?.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    adapter = UsersAdapter(requireActivity()) { user ->
      val action =
        UsersListFragmentDirections.actionUsersListFragmentToUpdateUserFragment(user.id!!)
      findNavController().navigate(action)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_list_users, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setSupportActionbar(getString(R.string.users_list_title))

    swipeRefreshLayout.setOnRefreshListener(this)

    presenter.attachView(this)

    usersRecyclerView.adapter = adapter
    usersRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

    addUserButton.setOnClickListener {
      findNavController().navigate(UsersListFragmentDirections.actionUsersListFragmentToCreateUserFragment())
    }

    presenter.loadUsers()
  }

  override fun onRefresh() {
    presenter.refreshUsers()
  }

  override fun showLoadingDialog() {
    swipeRefreshLayout.isRefreshing = true
  }

  override fun dismissLoadingDialog() {
    swipeRefreshLayout.isRefreshing = false
  }

  override fun onUsersLoaded(users: List<UserEntity>) {
    adapter.setData(users)
  }

  override fun onError(message: String) {
    Snackbar.make(swipeRefreshLayout, message, Snackbar.LENGTH_SHORT).show()
  }

  override fun onDestroyView() {
    presenter.detachView()

    super.onDestroyView()
  }
}