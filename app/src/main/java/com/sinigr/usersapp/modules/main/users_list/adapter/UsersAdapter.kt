package com.sinigr.usersapp.modules.main.users_list.adapter

import android.app.Activity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.modules.main.users_list.adapter.delegates.UserAdapterDelegate

typealias ClickListener = (UserEntity) -> Unit

class UsersAdapter(context: Activity, listener: ClickListener) :
  ListDelegationAdapter<List<UserEntity>>() {

  init {
    delegatesManager.addDelegate(
      UserAdapterDelegate(
        context,
        listener
      )
    )
  }

  fun setData(users: List<UserEntity>) {
    setItems(users)
    notifyDataSetChanged()
  }

}