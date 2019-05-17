package com.sinigr.usersapp.modules.main.edit_users.view.validation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.sinigr.usersapp.R
import com.sinigr.usersapp.utility.view_validation.Criteria
import com.sinigr.usersapp.utility.view_validation.Observer
import com.sinigr.usersapp.utility.view_validation.ViewValidator

class DefaultEditUserViewsValidator(
    private val context: Context,
    private val firstNameEditText: TextInputLayout,
    private val lastNameEditText: TextInputLayout,
    private val emailEditText: TextInputLayout
) {

    private lateinit var firstNameNotBlankValidator: ViewValidator<TextInputLayout>
    private lateinit var lastNameNotBlankValidator: ViewValidator<TextInputLayout>
    private lateinit var emailCorrectValidator: ViewValidator<TextInputLayout>

    init {
        initFirstNameValidator()

        initLastNameValidator()

        initEmailValidator()

        bindTextWatcher(firstNameEditText) {
            resetView(firstNameEditText)
        }

        bindTextWatcher(lastNameEditText) {
            resetView(lastNameEditText)
        }

        bindTextWatcher(emailEditText) {
            resetView(emailEditText)
        }
    }

    fun checkValidation(callback: ((isValid: Boolean) -> Unit)?) {
        validate()

        callback?.invoke(isValid())
    }

    private fun isValid(): Boolean {
        return !isBlankPredicate(firstNameEditText.editText) &&
                !isBlankPredicate(lastNameEditText.editText) &&
                isCorrectEmailPredicate(emailEditText.editText)
    }

    private fun validate() {
        firstNameNotBlankValidator.validate()
        lastNameNotBlankValidator.validate()
        emailCorrectValidator.validate()
    }

    private fun initFirstNameValidator() {
        firstNameNotBlankValidator = ViewValidator(
            Criteria(firstNameEditText)
                .test(object : Criteria.Condition<TextInputLayout> {
                    override fun evaluate(view: TextInputLayout): Boolean {
                        return !isBlankPredicate(view.editText)
                    }
                })
        )

        firstNameNotBlankValidator.observe(object : Observer<TextInputLayout>(firstNameEditText) {
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
        lastNameNotBlankValidator = ViewValidator(
            Criteria(lastNameEditText)
                .test(object : Criteria.Condition<TextInputLayout> {
                    override fun evaluate(view: TextInputLayout): Boolean {
                        return !isBlankPredicate(view.editText)
                    }
                })
        )

        lastNameNotBlankValidator.observe(object : Observer<TextInputLayout>(lastNameEditText) {
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
        emailCorrectValidator = ViewValidator(
            Criteria(emailEditText)
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

        emailCorrectValidator.observe(object : Observer<TextInputLayout>(emailEditText) {
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