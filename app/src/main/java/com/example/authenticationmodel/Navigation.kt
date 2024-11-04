package com.example.authenticationmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.authenticationmodel.ui.Screens.ForgotPasswordScreen
import com.example.authenticationmodel.ui.Screens.HomeScreen
import com.example.authenticationmodel.ui.Screens.LoginScreen
import com.example.authenticationmodel.ui.Screens.ResetPasswordScreen
import com.example.authenticationmodel.ui.Screens.SignUpScreen
import com.example.authenticationmodel.ui.Screens.VerifyScreen


@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", builder = {

        composable("login") {
            LoginScreen(modifier, navController)
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