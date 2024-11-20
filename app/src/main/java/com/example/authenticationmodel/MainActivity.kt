package com.example.authenticationmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.authenticationmodel.Auth.google.GoogleAuthModel
import com.example.authenticationmodel.Auth.google.GoogleAuthViewModel
import com.example.authenticationmodel.ui.theme.AuthenticationModelTheme
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthenticationModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    //val viewModel= viewModel<GoogleAuthViewModel>()
                    //val state by viewModel.state.collectAsStateWithLifecycle()
                    Navigation(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}
