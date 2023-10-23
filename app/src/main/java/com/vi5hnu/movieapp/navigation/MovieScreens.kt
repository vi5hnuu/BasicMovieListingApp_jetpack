package com.vi5hnu.movieapp.navigation

import java.lang.IllegalArgumentException

enum class MovieScreens {
    HomeScreen,
    MovieDetailScreen;

    companion object{
        fun fromRoute(route:String?):MovieScreens{
            return when(route?.substringAfter("/")){
                HomeScreen.name -> HomeScreen
                MovieDetailScreen.name -> MovieDetailScreen
                    null->HomeScreen
                    else -> throw IllegalArgumentException("Invalid route")
            }
        }
    }
}