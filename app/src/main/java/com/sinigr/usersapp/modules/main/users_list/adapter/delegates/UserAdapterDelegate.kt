package com.sinigr.usersapp.modules.main.users_list.adapter.delegates

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.sinigr.usersapp.R
import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.modules.main.users_list.adapter.ClickListener

class UserAdapterDelegate(
  private val context: Activity,
  private val listener: ClickListener
) :
  AbsListItemAdapterDelegate<UserEntity, UserEntity, UserAdapterDelegate.Holder>() {

  private var layoutInflater: LayoutInflater = context.layoutInflater

  override fun onCreateViewHolder(parent: ViewGroup): Holder {
    return Holder(
      layoutInflater.inflate(
        R.layout.item_user,
        parent,
        false
      )
    )
  }

  override fun isForViewType(
    item: UserEntity,
    items: MutableList<UserEntity>,
    position: Int
  ): Boolean {
    return true
  }

  override fun onBindViewHolder(item: UserEntity, holder: Holder, payloads: MutableList<Any>) {
    with(holder) {
      itemView.setOnClickListener {
        listener.invoke(item)
      }

      fullNameTextView.text =
        context.getString(R.string.full_name_format, item.firstName, item.lastName)
      emailTextView.text = item.email

      Glide.with(context)
        .load(item.avatarUrl)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.ic_default_user)
        .error(R.drawable.ic_default_user)
        .into(avatarImageView)
    }
  }

  class Holder(view: View) : RecyclerView.ViewHolder(view) {
    val avatarImageView: AppCompatImageView = view.findViewById(R.id.avatarImageView)
    val fullNameTextView: AppCompatTextView = view.findViewById(R.id.fullNameTextView)
    val emailTextView: AppCompatTextView = view.findViewById(R.id.emailTextView)
  }
}