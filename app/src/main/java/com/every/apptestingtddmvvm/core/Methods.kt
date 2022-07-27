package com.every.apptestingtddmvvm.core

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class Methods {

    fun closeKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}