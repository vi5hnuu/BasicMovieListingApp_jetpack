package com.vi5hnu.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vi5hnu.movieapp.screens.home.HomeScreen
import com.vi5hnu.movieapp.screens.movieDetail.MovieDetailScreen


@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name ){
        composable(MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable("${MovieScreens.MovieDetailScreen.name}/{id}", arguments = listOf(navArgument(name="id"){
            type= NavType.StringType
        })){
            MovieDetailScreen(navController = navController,id=it.arguments?.getString("id") ?: "unknown");
        }
    }
}