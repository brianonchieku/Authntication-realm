package com.example.authrealm

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authrealm.view.LoginPage
import com.example.authrealm.view.SignupPage

@Composable
fun Navigation(viewmodel: UserViewmodel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login page"){

        composable("login page"){
            LoginPage(viewmodel = viewmodel )
        }

        composable("signup page"){
            SignupPage(viewmodel = viewmodel, navController)
        }
    }
}

