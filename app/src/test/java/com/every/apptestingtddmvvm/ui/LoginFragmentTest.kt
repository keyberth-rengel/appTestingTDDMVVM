package com.every.apptestingtddmvvm.ui

import com.every.apptestingtddmvvm.core.MessageStatus
import com.every.apptestingtddmvvm.ui.auth.LoginFragment
import org.junit.Assert.*
import org.junit.Test

class LoginFragmentTest {

    private val username = "juan@empresa.com"
    private val password = "mi_password"

    @Test
    fun login_complete_returnComplete(){
        val result = LoginFragment().validateForm(username, password)

        assertEquals( MessageStatus.COMPLETED, result )
    }

    @Test
    fun login_completeEmailEmpty_returnComplete(){
        val result = LoginFragment().validateForm("", password)

        assertEquals( MessageStatus.EMAIL_IS_EMPTY, result )
    }

    @Test
    fun login_completeEmailInvalidate_returnComplete(){
        val result = LoginFragment().validateForm("juan@empresacom", password)

        assertEquals( MessageStatus.EMAIL_IS_INVALIDATE, result )
    }

    @Test
    fun login_completePasswordEmpty_returnComplete(){
        val result = LoginFragment().validateForm(username, "")

        assertEquals( MessageStatus.PASSWORD_IS_EMPTY, result )
    }

    @Test
    fun login_completePasswordInvalidate_returnComplete(){
        val result = LoginFragment().validateForm(username, "12345")

        assertEquals( MessageStatus.PASSWORD_IS_INVALIDATE, result )
    }
}