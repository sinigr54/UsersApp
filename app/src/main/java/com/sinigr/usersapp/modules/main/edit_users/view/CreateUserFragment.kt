package com.sinigr.usersapp.modules.main.edit_users.view

import androidx.appcompat.app.AlertDialog
import com.sinigr.usersapp.R
import com.sinigr.usersapp.modules.main.edit_users.di.EditUserComponent
import kotlinx.android.synthetic.main.fragment_edit_user.*

class CreateUserFragment : BaseEditUserFragment() {

  override fun onComponentCreated() {
    component<EditUserComponent>()?.inject(this)
  }

  override fun setTitle() {
    val title = getString(R.string.edit_user_title_create)
    setSupportActionbar(title, true)
  }

  override fun setButtonTitle() {
    val title = getString(R.string.edit_user_button_title_create)
    saveButton.text = title
  }

  override fun sendData() {
    presenter.createUser(
      firstNameEditText.text.toString().trim(), lastNameEditText.text.toString().trim(),
      emailEditText.text.toString().trim()
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