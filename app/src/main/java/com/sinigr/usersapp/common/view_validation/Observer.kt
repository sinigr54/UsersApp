package com.sinigr.usersapp.common.view_validation

import android.view.View

abstract class Observer<T : View>(private val view: T) {

  fun update(validationResult: ViewValidator.ValidationResult) {
    onValidationCompleted(view, validationResult)
  }

  abstract fun onValidationCompleted(view: T, validationResult: ViewValidator.ValidationResult)

}