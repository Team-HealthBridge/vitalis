package com.healthbridge.vitalis.feature_auth.ui

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.healthbridge.vitalis.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuthRepository @Inject constructor(
) {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()



    @Singleton
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    @Singleton
    @Provides
    fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), firebaseAuth.currentUser == null)

    @Singleton
    @Provides
    suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): Boolean{
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            return true
        } catch (e: Exception) {
            println("Exception $e")
            return false
        }
    }

    @Singleton
    @Provides
    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): Boolean {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            return true
        } catch (e: Exception) {
            println("Exception $e")
            return false
        }
    }

    @Singleton
    @Provides
    suspend fun firebaseSignInWithGoogle(account: GoogleSignInAccount): Boolean {
        try {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            return authResult.user != null
        } catch (e: Exception) {
            println("Exception $e")
            return false
        }

    }

    companion object{
        fun createGoogleSignInClient(context: Context): GoogleSignInClient {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            return GoogleSignIn.getClient(context, gso)
        }


    }

    @Singleton
    @Provides
    fun signOut(): Boolean {
        try {
            firebaseAuth.signOut()
            return true
        } catch (e: Exception) {
            throw e
        }
    }

    @Singleton
    @Provides
    suspend fun sendPasswordResetEmail(email: String) {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
        } catch (e: Exception) {
            throw e
        }
    }

    @Singleton
    @Provides
    suspend fun updateUserDetails(name: String, phone: String, email: String, password: String, password2: String): Boolean{
        return try {
            firebaseAuth.currentUser?.updateProfile(
                com.google.firebase.auth.UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
            )?.await()
            firebaseAuth.currentUser?.updateEmail(email)?.await()
            if( password != "" && password == password2){
                firebaseAuth.currentUser?.updatePassword(password)?.await()
            }
            true
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }

}
