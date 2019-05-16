package com.sinigr.usersapp.modules.main.edit_users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sinigr.usersapp.R
import com.sinigr.usersapp.base.BaseFragment
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.modules.main.edit_users.presenter.IEditUserPresenter
import kotlinx.android.synthetic.main.fragment_edit_user.*
import org.koin.android.ext.android.inject

abstract class BaseEditUserFragment : BaseFragment(), IEditUserView {

    companion object {
        private const val TAG = "EditUserFragment"

        private const val DEFAULT_ID = -1L
    }

    protected val presenter: IEditUserPresenter by inject()
    protected val args: UpdateUserFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        if (args.id != DEFAULT_ID) {
            presenter.getUser(args.id)
        }

        btnSave.setOnClickListener {
            sendData()
        }

        setViewTitles()
    }

    protected abstract fun sendData()

    protected abstract fun setTitle()

    protected abstract fun setButtonTitle()

    protected abstract fun successEditAction()

    private fun setViewTitles() {
        setTitle()
        setButtonTitle()
    }

    private fun initializeUserInfo(user: UserEntity) {
        Glide.with(requireActivity())
            .load(user.avatarUrl)
            .centerCrop()
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.ic_default_avatar)
            .into(ivAvatar)

        etFirstName.setText(user.firstName)
        etLastName.setText(user.lastName)
        etEmail.setText(user.email)
    }

    override fun onUserLoaded(user: UserEntity) {
        initializeUserInfo(user)
    }

    override fun onUserUpdated(user: UserEntity) {
        successEditAction()
    }

    override fun onDestroyView() {
        presenter.detachView()

        super.onDestroyView()
    }

}