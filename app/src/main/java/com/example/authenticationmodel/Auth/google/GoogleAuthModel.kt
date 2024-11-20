package com.example.authenticationmodel.Auth.google

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.authenticationmodel.R
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthModel(
    private val context: Context,
    private val oneTapClient: SignInClient
)
{
    private val auth = Firebase.auth

    suspend fun signIn():IntentSender?
    {
        val result = try{
            oneTapClient.beginSignIn(
                beginSignInRequest()
            ).await()
        }
        catch(e: Exception)
        {
            e.printStackTrace()
            if(e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender

    }

    suspend fun SignInWithIntent(intent: Intent):SignInResult{
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken,null)

        return try{
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        UserId = uid,
                        UserName = displayName,
                        UserProfilepictureURL = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )

        }
        catch(e: Exception)
        {
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )

        }
    }

    suspend fun SignOut()
    {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        }
        catch(e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run{
        UserData(
            UserId = uid,
            UserName = displayName,
            UserProfilepictureURL = photoUrl?.toString()
        )
    }

    private fun beginSignInRequest(): BeginSignInRequest{

        return BeginSignInRequest
            .builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.default_webclient_id))
                    .build()
        ).build()
    }

}