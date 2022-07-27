package com.every.apptestingtddmvvm.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.every.apptestingtddmvvm.R
import com.every.apptestingtddmvvm.application.ApplicationDataBase
import com.every.apptestingtddmvvm.core.MessageStatus
import com.every.apptestingtddmvvm.core.Methods
import com.every.apptestingtddmvvm.core.SharedPrefe
import com.every.apptestingtddmvvm.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var mNavController: NavController
    private lateinit var mSharedPrefe: SharedPrefe

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentLoginBinding.bind(view)
        mNavController = Navigation.findNavController(view)
        mSharedPrefe = ApplicationDataBase.appPrefs

        validateIfExistASession()

        mBinding.btnLogin.setOnClickListener {
            val userName = mBinding.inputUserName.text.toString().trim()
            val password = mBinding.inputPassword.text.toString().trim()

            val result = validateForm(userName, password)
            if (result == MessageStatus.COMPLETED) {
                sendForm(userName)
            } else {
                generateMessageStatus(result)
            }
        }
    }

    private fun validateIfExistASession() {
        val res = mSharedPrefe.getSession()
        if (res.isNotEmpty()) {
            createToast("Bienvenido $res")
            navigationToHome()
        }
    }

    fun validateForm(userName: String, password: String): MessageStatus {

        return if (userName.isEmpty()) { //validate username
            MessageStatus.EMAIL_IS_EMPTY
        } else if (!isValidateEmail(userName)) {
            MessageStatus.EMAIL_IS_INVALIDATE
        } else if (password.isEmpty()) {  //validate password
            MessageStatus.PASSWORD_IS_EMPTY
        } else if (!isValidatePassword(password)) {
            MessageStatus.PASSWORD_IS_INVALIDATE
        } else {
            MessageStatus.COMPLETED
        }
    }

    private fun generateMessageStatus(status: MessageStatus) {
        when (status) {
            MessageStatus.EMAIL_IS_EMPTY -> createToast("El username no puede estar en blanco")
            MessageStatus.EMAIL_IS_INVALIDATE -> createToast("El username no es valido")
            MessageStatus.PASSWORD_IS_EMPTY -> createToast("El password no puede estar en blanco")
            MessageStatus.PASSWORD_IS_INVALIDATE -> createToast("El password debe contener al menos 6 caracteres")
            else -> createToast("Ha ocurrido un error")
        }
    }

    private fun isValidateEmail(email: String): Boolean {
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return EMAIL_REGEX.toRegex().matches(email)
    }

    private fun isValidatePassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun sendForm(userName: String) {
        Methods().closeKeyboard(
            requireContext(),
            mBinding.inputPassword
        )
        createToast("Bienvenido $userName")
        mSharedPrefe.setSession(userName)
        cleanForm()
        navigationToHome()
    }

    private fun createToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun cleanForm() {
        mBinding.inputPassword.text?.clear()
        mBinding.inputUserName.text?.clear()
    }

    private fun navigationToHome() {
        mNavController.navigate(R.id.action_login_to_home)
    }

}