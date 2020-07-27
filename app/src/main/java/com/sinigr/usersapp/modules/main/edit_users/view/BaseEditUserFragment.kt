package com.sinigr.usersapp.modules.main.edit_users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.sinigr.usersapp.R
import com.sinigr.usersapp.common.di.InjectionComponentFactory
import com.sinigr.usersapp.common.di.SubcomponentFactory
import com.sinigr.usersapp.common.fragment.BaseFragment
import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.modules.main.di.MainComponent
import com.sinigr.usersapp.modules.main.edit_users.presenter.IEditUserPresenter
import com.sinigr.usersapp.modules.main.edit_users.view.validation.DefaultEditUserViewsValidator
import kotlinx.android.synthetic.main.fragment_edit_user.*
import javax.inject.Inject

abstract class BaseEditUserFragment : BaseFragment(), IEditUserView {

  companion object {
    private const val DEFAULT_ID = -1L
  }

  @Inject
  lateinit var requestManager: RequestManager

  @Inject
  lateinit var presenter: IEditUserPresenter

  protected val args: UpdateUserFragmentArgs by navArgs()

  override fun createComponentFactory(): InjectionComponentFactory<*, *>? {
    return SubcomponentFactory.create { parent: MainComponent ->
      parent.createEditUserComponent()
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_edit_user, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    presenter.attachView(this)

    if (args.id != DEFAULT_ID) {
      presenter.getUser(args.id)
    }

    val viewsValidator = DefaultEditUserViewsValidator(
      requireContext(),
      firstNameTextInputLayout,
      lastNameTextInputLayout,
      emailTextInputLayout
    )

    saveButton.setOnClickListener {
      viewsValidator.checkValidation { isValid ->
        if (isValid) {
          sendData()
        }
      }
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
    requestManager
      .load(user.avatarUrl)
      .apply(RequestOptions.circleCropTransform())
      .error(R.drawable.ic_default_user)
      .into(avatarImageView)

    firstNameEditText.setText(user.firstName)
    lastNameEditText.setText(user.lastName)
    emailEditText.setText(user.email)
  }

  override fun onUserLoaded(user: UserEntity) {
    initializeUserInfo(user)
  }

  override fun onUserEdited(user: UserEntity) {
    successEditAction()
  }

  override fun onError(message: String) {
    Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
  }

  override fun onDestroyView() {
    presenter.detachView()

    super.onDestroyView()
  }

}