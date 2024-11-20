package com.example.authenticationmodel.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.authenticationmodel.Auth.google.GoogleAuthModel
import com.example.authenticationmodel.Auth.google.GoogleAuthViewModel
import com.example.authenticationmodel.Auth.google.SignInState
import com.example.authenticationmodel.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier ,
                navController: NavController,
                googleAuthViewModel: GoogleAuthViewModel,
                state:SignInState,
                onSignInClick:()->Unit) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = state.SignInerror) {
        state.SignInerror?.let {error->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }


    }
    val myCustomFont = FontFamily(Font(R.font.robotobold))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .wrapContentSize()
            .padding(top = 150.dp)) {
            Text(
                text = "Login",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                fontFamily = myCustomFont
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        Box() {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                value = username,
                onValueChange = { username = it },
                label = {
                    Text(text = "Username")
                },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.background),
                    //unfocusedBorderColor = colorResource(id = R.color.black),
                    focusedLabelColor = colorResource(id = R.color.background),
                    //unfocusedLabelColor = colorResource(id = R.color.black),
                    cursorColor = colorResource(id = R.color.background)
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box() {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(text = "password")
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible })
                    {
                        Icon(imageVector = image, description)
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.background),
                    //unfocusedBorderColor = colorResource(id = R.color.black),
                    focusedLabelColor = colorResource(id = R.color.background),
                    //unfocusedLabelColor = colorResource(id = R.color.black),
                    cursorColor = colorResource(id = R.color.background)
                )
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background)),
            onClick = {
                //authViewModel.login(username,password)
            },
            //enabled = authState.value != AuthState.Loading
        ) {

            Text(text = "Login", lineHeight = 35.sp, fontFamily = myCustomFont, fontSize = 18.sp)

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            border = BorderStroke(0.5.dp,Color.Black),

            onClick = {
                onSignInClick()
            },

        ) {
            Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google_logo_removebg_preview),
                    contentDescription = "Google Logo",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(1.dp)
                )
                Text(
                    text = "Login with Google",
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color.Black,
                     fontSize = 18.sp

                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    //navController.navigate("signup")
                },
            ) {
                Text(text = "Don't have an account, SignUp", fontSize = 15.sp )
            }

    }
}
