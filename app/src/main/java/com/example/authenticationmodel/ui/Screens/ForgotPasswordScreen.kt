package com.example.authenticationmodel.ui.Screens

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.authenticationmodel.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(modifier: Modifier,navController: NavController)
{
    val myCustomFont = FontFamily(Font(R.font.robotobold))
    var email by rememberSaveable{ mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 60.dp)
        ) {
            Text(
                text = "Forgot Password",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                fontFamily = myCustomFont
            )
        }

        Text(
            modifier = Modifier.padding(top = 20.dp, start = 30.dp,end = 30.dp),
            textAlign = TextAlign.Center,
            //handle the email and make it bold
            text = "No worries! Enter your email address below and we will send you a code to reset password.",
            fontFamily = FontFamily(Font(R.font.robotoregular))
        )



        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "E-mail",
                    fontFamily = FontFamily(Font(R.font.robotoregular))
                )


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(text = "Enter your email",color = colorResource(id = R.color.faded), fontFamily = FontFamily(Font(R.font.robotoregular)))
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = colorResource(id = R.color.faded),
                        focusedBorderColor = colorResource(id = R.color.background),
                        cursorColor = colorResource(id = R.color.background),
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val annotatedString = buildAnnotatedString {
            append("Didn't receive the code? ")
            withStyle(style = SpanStyle(color = Color.Gray, textDecoration = TextDecoration.Underline)) {
                append("Resend Code")
            }
            addStringAnnotation(
                tag = "clickable1",
                start = 25,
                end = 36,
                annotation = "click me"
            )

        }
        Column(
            modifier = Modifier.padding(start = 50.dp, end = 30.dp, bottom = 5.dp, top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "clickable",
                        start = offset,
                        end = offset
                    )
                        .firstOrNull()?.let { annotation ->
                            // Handle the action for the first clickable word
                            println("First clickable word clicked: ${annotation.item}")
                            // Add your specific action here

                        }
                }
            )
        }
        //handle time
        Text("Resend code in time", fontFamily = FontFamily(Font(R.font.robotoregular)), modifier = Modifier.padding(bottom = 10.dp))

        Column(modifier = Modifier.weight(1f).padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,) {
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

                Text(
                    text = "Send Reset Instructions",
                    lineHeight = 35.sp,
                    fontFamily = myCustomFont,
                    fontSize = 18.sp
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }


}
