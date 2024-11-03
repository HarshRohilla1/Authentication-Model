package com.example.authenticationmodel.ui.Screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authenticationmodel.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    //val authState = authViewModel.authState.observeAsState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val myCustomFont = FontFamily(Font(R.font.robotobold))

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
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create new Password", fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            fontFamily = myCustomFont,
            modifier = Modifier.padding(bottom = 10.dp))

            Text(
                modifier = Modifier.padding(top = 20.dp, start = 30.dp, end = 30.dp),
                textAlign = TextAlign.Center,
                //handle the email and make it bold
                text = "Please enter and confirm your new password.",
                fontFamily = FontFamily(Font(R.font.robotoregular))
            )
            Text(
                modifier = Modifier.padding(

                    start = 30.dp,
                    end = 30.dp,
                    bottom = 40.dp
                ),
                textAlign = TextAlign.Center,
                //handle the email and make it bold
                text = "You will need to login after you reset.",
                fontFamily = FontFamily(Font(R.font.robotoregular))
            )

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Password",modifier= Modifier.padding(bottom = 5.dp), fontFamily = FontFamily(Font(R.font.robotoregular)))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text("Password",color = colorResource(id = R.color.faded))
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = colorResource(id = R.color.faded),
                        focusedBorderColor = colorResource(id = R.color.background),
                        cursorColor = colorResource(id = R.color.background),
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Confirm Password",modifier= Modifier.padding(bottom = 5.dp), fontFamily = FontFamily(Font(R.font.robotoregular)))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text("Password",color = colorResource(id = R.color.faded))
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = colorResource(id = R.color.faded),
                        focusedBorderColor = colorResource(id = R.color.background),
                        cursorColor = colorResource(id = R.color.background),
                    )
                )
            }
        }


        Column(modifier = Modifier.weight(1f).padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background)),
                onClick = {
                    //authViewModel.login(username,password)
                },
                //enabled = authState.value != AuthState.Loading
            ) {

                Text(
                    text = "Reset Password",
                    lineHeight = 35.sp,
                    fontFamily = myCustomFont,
                    fontSize = 18.sp
                )

            }
        }


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
fun ResetPasswordScreenPreview() {
    ResetPasswordScreen()

}