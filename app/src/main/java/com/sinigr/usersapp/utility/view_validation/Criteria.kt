package com.sinigr.usersapp.utility.view_validation

import android.view.View

class Criteria<T : View>(private val view: T) {

    interface Condition<T : View> {
        fun evaluate(view: T): Boolean
    }

    interface EvalCompleteListener {
        fun onComplete(validationResult: ViewValidator.ValidationResult)
    }

    private val tests: MutableSet<Condition<T>> = hashSetOf()

    fun test(condition: Condition<T>): Criteria<T> {
        tests.add(condition)

        return this
    }

    fun evaluate(listener: EvalCompleteListener) {
        val result = tests.filter { it.evaluate(view) }.size == tests.size

        if (result) {
            listener.onComplete(ViewValidator.ValidationResult.Valid)
        } else {
            listener.onComplete(ViewValidator.ValidationResult.Invalid)
        }
    }

}