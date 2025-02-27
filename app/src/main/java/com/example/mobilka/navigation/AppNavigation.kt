package com.example.mobilka.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.mobilka.views.LoginView
import com.example.mobilka.views.MainView
import com.example.mobilka.views.RegistrationView
import com.example.mobilka.views.SladerView

@Composable
fun AppNavigation(navController: NavHostController, startDestination: String) {
    NavHost(navController, startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) }) {
        composable("registration") { RegistrationView(navController) }
        composable("login") { LoginView(navController)  }
        composable("mainPage") { MainView(navController) }
        composable("sladerPage"){ SladerView(navController)}


    }
}


