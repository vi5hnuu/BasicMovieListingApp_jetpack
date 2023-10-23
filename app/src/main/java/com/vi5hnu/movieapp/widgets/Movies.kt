package com.vi5hnu.movieapp.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vi5hnu.movieapp.navigation.MovieScreens

@Composable
fun Movies(navController: NavController){
    val movies= com.vi5hnu.movieapp.model.Movie.movies
    LazyColumn(modifier = Modifier, contentPadding = PaddingValues(7.dp)) {
        itemsIndexed(movies) { _, movie ->
            Movie(movie = movie) {
                navController.navigate(
                    route = "${MovieScreens.MovieDetailScreen.name}/${movie.id}"
                )
            }
        }
    }
}