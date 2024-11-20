package com.example.authenticationmodel.Auth.google

data class SignInResult(
    val data:UserData?,
    val errorMessage:String?
)

data class UserData(
    val UserId:String?,
    val UserName:String?,
    val UserProfilepictureURL:String?
)
