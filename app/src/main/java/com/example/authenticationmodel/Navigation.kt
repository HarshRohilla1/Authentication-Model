package com.example.authenticationmodel

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.IntentSender
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.authenticationmodel.Auth.google.GoogleAuthViewModel
import com.example.authenticationmodel.ui.Screens.ForgotPasswordScreen
import com.example.authenticationmodel.ui.Screens.HomeScreen
import com.example.authenticationmodel.ui.Screens.LoginScreen
import com.example.authenticationmodel.ui.Screens.ResetPasswordScreen
import com.example.authenticationmodel.ui.Screens.SignUpScreen
import com.example.authenticationmodel.ui.Screens.VerifyScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.authenticationmodel.Auth.google.GoogleAuthModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.contracts.contract



@Composable
fun Navigation(modifier: Modifier) {
    val scope = rememberCoroutineScope()

    val context= LocalContext.current
    val _googleAuthModel by lazy {
        GoogleAuthModel(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", builder = {

        composable("login") {

            val viewModel  = viewModel<GoogleAuthViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val launcher= rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = {result->
                    if(result.resultCode==RESULT_OK)
                    {

                        scope.launch{
                        val signInResult =_googleAuthModel.SignInWithIntent(
                            intent = result.data ?: return@launch
                        )
                            viewModel.signInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1=state.isSignInSuccessful) {
                if(state.isSignInSuccessful)
                {
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            LoginScreen(modifier,
                navController,
                viewModel,
                state = state,
                onSignInClick = {
                scope.launch{
                    val signInIntentSender =_googleAuthModel.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender?: return@launch
                        ).build()
                    )
                }
                })
        }
        composable("signup") {
            SignUpScreen(modifier, navController)
        }
        composable("home") {
            HomeScreen(modifier, navController)
        }
        composable("forgotPassword") {
            ForgotPasswordScreen(modifier, navController)
        }
        composable("resetPassword") {
            ResetPasswordScreen(modifier, navController)
        }
        composable("verify") {
            VerifyScreen(modifier, navController)
        }

    }
    )

}