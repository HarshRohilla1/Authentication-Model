package com.example.authenticationmodel.Auth.google

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GoogleAuthViewModel(): ViewModel()
{
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun signInResult(result: SignInResult)
    {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data!=null,
                SignInerror = result.errorMessage
            )
        }

    }

    fun resetState()
    {
        _state.update { SignInState() }
    }
}