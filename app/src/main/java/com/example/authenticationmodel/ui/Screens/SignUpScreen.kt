package com.example.authenticationmodel.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SignUpScreen(modifier: Modifier = Modifier)
{
    var username by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable{ mutableStateOf("") }
    //val authState = authViewModel.authState.observeAsState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

//    LaunchedEffect(authState.value) {
//        when(authState.value) {
//            is AuthState.Authenticated -> navController.navigate("homepage")
//            is AuthState.Error -> Toast.makeText(context,
//                (authState.value as AuthState.Error).message, Toast.LENGTH_LONG).show()
//
//            else -> Unit
//        }

 //   }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up Page")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = username,
            onValueChange = {username= it},
            label = {
                Text(text = "username")
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = password,
            onValueChange = {password= it},
            label = {
                Text(text = "password")
            },
            visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

//        Button(onClick = {
//            //authViewModel.SignUp(username,password)
//        }
//           // /*enabled = authState.value != AuthState.Loading)*/ {
////
////            Text(text = "Sign Up")
////
////        }
//        TextButton(onClick = {
//            //navController.navigate("Login")
//
//        }) {
//            Text(text = "Already have an account, LogIn")
//
//        }
    }

}

@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(modifier = Modifier)

}