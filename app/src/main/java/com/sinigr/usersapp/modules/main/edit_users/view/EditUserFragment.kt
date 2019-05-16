package com.sinigr.usersapp.modules.main.edit_users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sinigr.usersapp.R
import com.sinigr.usersapp.base.BaseFragment
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.modules.main.edit_users.presenter.IEditUserPresenter
import kotlinx.android.synthetic.main.fragment_edit_user.*
import org.koin.android.ext.android.inject

class EditUserFragment : BaseFragment(), IEditUserView {

    companion object {
        private const val DEFAULT_ARG = -1L
    }

    private val presenter: IEditUserPresenter by inject()
    private val args: EditUserFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun onUserLoaded(user: UserEntity) {

    }

    override fun onUserUpdated(user: UserEntity) {

    }

    override fun onDestroyView() {
        presenter.detachView()

        super.onDestroyView()
    }
}