package com.sinigr.usersapp.modules.main.edit_users.view.validation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.sinigr.usersapp.R
import com.sinigr.usersapp.common.view_validation.Criteria
import com.sinigr.usersapp.common.view_validation.Observer
import com.sinigr.usersapp.common.view_validation.ViewValidator

class DefaultEditUserViewsValidator(
  private val context: Context,
  private val firstNameTextInputLayout: TextInputLayout,
  private val lastNameTextInputLayout: TextInputLayout,
  private val emailEditTextInputLayout: TextInputLayout
) {

  private lateinit var firstNameIsNotBlankValidator: ViewValidator<TextInputLayout>
  private lateinit var lastNameIsNotBlankValidator: ViewValidator<TextInputLayout>
  private lateinit var emailIsCorrectValidator: ViewValidator<TextInputLayout>

  init {
    initFirstNameValidator()

    initLastNameValidator()

    initEmailValidator()

    bindTextWatcher(firstNameTextInputLayout) {
      resetView(firstNameTextInputLayout)
    }

    bindTextWatcher(lastNameTextInputLayout) {
      resetView(lastNameTextInputLayout)
    }

    bindTextWatcher(emailEditTextInputLayout) {
      resetView(emailEditTextInputLayout)
    }
  }

  fun checkValidation(callback: ((isValid: Boolean) -> Unit)?) {
    validate()

    callback?.invoke(isValid())
  }

  private fun isValid(): Boolean {
    return !isBlankPredicate(firstNameTextInputLayout.editText) &&
        !isBlankPredicate(lastNameTextInputLayout.editText) &&
        isCorrectEmailPredicate(emailEditTextInputLayout.editText)
  }

  private fun validate() {
    firstNameIsNotBlankValidator.validate()
    lastNameIsNotBlankValidator.validate()
    emailIsCorrectValidator.validate()
  }

  private fun initFirstNameValidator() {
    firstNameIsNotBlankValidator =
      ViewValidator(
        Criteria(
          firstNameTextInputLayout
        )
          .test(object : Criteria.Condition<TextInputLayout> {
            override fun evaluate(view: TextInputLayout): Boolean {
              return !isBlankPredicate(view.editText)
            }
          })
      )

    firstNameIsNotBlankValidator.observe(object :
      Observer<TextInputLayout>(firstNameTextInputLayout) {
      override fun onValidationCompleted(
        view: TextInputLayout,
        validationResult: ViewValidator.ValidationResult
      ) {
        if (validationResult == ViewValidator.ValidationResult.Invalid) {
          view.isErrorEnabled = true
          view.error = context.getString(R.string.edit_user_error_first_name)
        } else {
          resetView(view)
        }
      }
    })
  }

  private fun initLastNameValidator() {
    lastNameIsNotBlankValidator =
      ViewValidator(
        Criteria(
          lastNameTextInputLayout
        )
          .test(object : Criteria.Condition<TextInputLayout> {
            override fun evaluate(view: TextInputLayout): Boolean {
              return !isBlankPredicate(view.editText)
            }
          })
      )

    lastNameIsNotBlankValidator.observe(object :
      Observer<TextInputLayout>(lastNameTextInputLayout) {
      override fun onValidationCompleted(
        view: TextInputLayout,
        validationResult: ViewValidator.ValidationResult
      ) {
        if (validationResult == ViewValidator.ValidationResult.Invalid) {
          view.isErrorEnabled = true
          view.error = context.getString(R.string.edit_user_error_last_name)
        } else {
          resetView(view)
        }
      }
    })
  }

  private fun initEmailValidator() {
    emailIsCorrectValidator =
      ViewValidator(
        Criteria(
          emailEditTextInputLayout
        )
          .test(object : Criteria.Condition<TextInputLayout> {
            override fun evaluate(view: TextInputLayout): Boolean {
              return !isBlankPredicate(view.editText)
            }
          })
          .test(object : Criteria.Condition<TextInputLayout> {
            override fun evaluate(view: TextInputLayout): Boolean {
              return isCorrectEmailPredicate(view.editText)
            }
          })
      )

    emailIsCorrectValidator.observe(object : Observer<TextInputLayout>(emailEditTextInputLayout) {
      override fun onValidationCompleted(
        view: TextInputLayout,
        validationResult: ViewValidator.ValidationResult
      ) {
        if (validationResult == ViewValidator.ValidationResult.Invalid) {
          view.isErrorEnabled = true
          view.error = context.getString(R.string.edit_user_error_email)
        } else {
          resetView(view)
        }
      }
    })
  }

  private fun bindTextWatcher(
    view: TextInputLayout,
    action: DefaultEditUserViewsValidator.(TextInputLayout) -> Unit
  ) {
    view.editText?.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(p0: Editable?) {

      }

      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

      }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        action(view)
      }
    })
  }

  private fun resetView(view: TextInputLayout) {
    if (view.isErrorEnabled) {
      view.isErrorEnabled = false
      view.error = null
    }
  }

  private fun isBlankPredicate(view: EditText?): Boolean {
    return view?.text.toString().trim().isBlank()
  }

  private fun isCorrectEmailPredicate(view: EditText?): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(view?.text.toString().trim()).matches()
  }

}