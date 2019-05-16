package com.sinigr.usersapp.utility.view_validation

import android.view.View

class ViewValidator<T : View>(private val criteria: Criteria<T>) {

    enum class ValidationResult {
        Valid,
        Invalid
    }

    private val observers: MutableSet<Observer<T>> = hashSetOf()

    fun observe(observer: Observer<T>) {
        observers.add(observer)
    }

    fun validate() {
        criteria.evaluate(object : Criteria.EvalCompleteListener {
            override fun onComplete(validationResult: ValidationResult) {
                observers.forEach { it.update(validationResult) }
            }
        })
    }
}