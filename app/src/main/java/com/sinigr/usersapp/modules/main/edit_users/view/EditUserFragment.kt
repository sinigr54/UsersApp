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

class EditUserFragment : BaseFragment(), IEditUserView {

    companion object {
        private const val DEFAULT_ID = -1L
    }

    enum class EditMode {
        Create,
        Update
    }

    private val presenter: IEditUserPresenter by inject()
    private val args: EditUserFragmentArgs by navArgs()

    private lateinit var editMode: EditMode

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        if (args.id != DEFAULT_ID) {
            editMode = EditMode.Update
            presenter.getUser(args.id)
        } else {
            editMode = EditMode.Create
        }

        setViewTitles()
    }

    private fun setViewTitles() {
        setTitle()
        setButtonTitle()
    }

    private fun setTitle() {
        val title = when (editMode) {
            EditMode.Create -> getString(R.string.edit_user_title_create)
            EditMode.Update -> getString(R.string.edit_user_title_edit)
        }

        setSupportActionbar(title, true)
    }

    private fun setButtonTitle() {
        val title = when (editMode) {
            EditMode.Create -> getString(R.string.edit_user_button_title_create)
            EditMode.Update -> getString(R.string.edit_user_button_title_edit)
        }

        btnSave.text = title
    }

    private fun initializeUserInfo(user: UserEntity) {
        Glide.with(requireActivity())
            .load(user.avatarUrl)
            .centerCrop()
            .apply(RequestOptions.circleCropTransform())
            .into(ivAvatar)

        etFirstName.setText(user.firstName)
        etLastName.setText(user.lastName)
        etEmail.setText(user.email)
    }

    override fun onUserLoaded(user: UserEntity) {
        initializeUserInfo(user)
    }

    override fun onUserUpdated(user: UserEntity) {

    }

    override fun onDestroyView() {
        presenter.detachView()

        super.onDestroyView()
    }
}