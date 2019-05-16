package com.sinigr.usersapp.modules.main.edit_users.view

import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.sinigr.usersapp.R
import kotlinx.android.synthetic.main.fragment_edit_user.*

class CreateUserFragment : BaseEditUserFragment() {

    override fun setTitle() {
        val title = getString(R.string.edit_user_title_create)
        setSupportActionbar(title, true)
    }

    override fun setButtonTitle() {
        val title = getString(R.string.edit_user_button_title_create)
        btnSave.text = title
    }

    override fun sendData() {
        presenter.createUser(
            etFirstName.text.toString().trim(), etLastName.text.toString().trim(),
            etEmail.text.toString().trim()
        )
    }

    override fun successEditAction() {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.edit_user_snack_bar_message_create)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                requireActivity().onBackPressed()
            }
            .show()
    }

}