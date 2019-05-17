package com.sinigr.usersapp.modules.main.edit_users.view

import com.google.android.material.snackbar.Snackbar
import com.sinigr.usersapp.R
import kotlinx.android.synthetic.main.fragment_edit_user.*

class UpdateUserFragment : BaseEditUserFragment() {

    override fun setTitle() {
        val title = getString(R.string.edit_user_title_edit)
        setSupportActionbar(title, true)
    }

    override fun setButtonTitle() {
        val title = getString(R.string.edit_user_button_title_edit)
        saveButton.text = title
    }

    override fun sendData() {
        presenter.updateUser(
            args.id,
            firstNameEditText.text.toString().trim(), lastNameEditText.text.toString().trim(),
            emailEditText.text.toString().trim()
        )
    }

    override fun successEditAction() {
        Snackbar.make(root, R.string.edit_user_snack_bar_message_edit, Snackbar.LENGTH_SHORT).show()
    }

}