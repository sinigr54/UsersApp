package com.sinigr.usersapp.modules.main.edit_users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinigr.usersapp.base.BaseFragment
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.modules.main.edit_users.presenter.IEditUserPresenter
import org.koin.android.ext.android.inject

class EditUserFragment : BaseFragment(), IEditUserView {

    enum class Mode {
        CREATE, UPDATE
    }

    companion object {
        private const val ARG_USER = "edit_user_fragment_arg_user"

        fun newInstance() : EditUserFragment {
            return EditUserFragment()
        }

        fun newInstance(user: UserEntity): EditUserFragment {
            return EditUserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, user)
                }
            }
        }
    }

    private val presenter: IEditUserPresenter by inject()

    private var user: UserEntity? = null
    private var mode: Mode = Mode.CREATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null && arguments?.containsKey(ARG_USER) == true) {
            user = arguments?.get(ARG_USER) as UserEntity
            mode = Mode.UPDATE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        presenter.back()
    }

    override fun onDestroyView() {
        presenter.detachView()

        super.onDestroyView()
    }

}