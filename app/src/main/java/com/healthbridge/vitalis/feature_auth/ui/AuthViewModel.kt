package com.healthbridge.vitalis.feature_auth.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {


    init {
        getAuthState()
    }

    fun getAuthState() = authRepository.getAuthState(viewModelScope)

    val currentUser = authRepository.currentUser

    val isSignedIn = mutableStateOf(false)

    fun firebaseSignUpWithEmailAndPassword(email: String, password: String, password2: String, name: String): Boolean{
        try {
            return if (validateForm(email, password, password2)) {
                viewModelScope.launch {
                    authRepository.firebaseSignUpWithEmailAndPassword(email, password, name)
                }
                true
            } else {
                false
            }
        } catch (e: Exception) {
            println("Exception $e")
            throw e
        }
    }

    fun firebaseSignInWithEmailAndPassword(email: String, password: String): Boolean {
        return try {
            viewModelScope.launch {
                authRepository.firebaseSignInWithEmailAndPassword(email, password)
            }
            true
        } catch (e: Exception) {
            println("Exception $e")
            throw e
        }

    }

    fun signInWithgoogle(account: GoogleSignInAccount){
        viewModelScope.launch {
            val success = authRepository.firebaseSignInWithGoogle(account)
            isSignedIn.value = success

        }
    }

    fun firebaseSignOut() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

    fun validateForm(email: String, password: String, password2: String): Boolean {
        if (email.isEmpty()) {
            return false
        }
        if (password.isEmpty()) {
            return false
        }
        if (password2.isEmpty()) {
            return false
        }
        if (password != password2) {
            return false
        }

        return true

    }

    fun updateUserDetails(name: String, phone: String, email: String, password: String, password2: String): Boolean {
        return try {
            viewModelScope.launch {
                authRepository.updateUserDetails(name, phone, email, password, password2)
            }
            true
        } catch (e: Exception) {
            println("Exception $e")
            false
        }

    }


}