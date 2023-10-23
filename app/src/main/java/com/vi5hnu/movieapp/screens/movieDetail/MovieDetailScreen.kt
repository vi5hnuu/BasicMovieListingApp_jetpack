package com.vi5hnu.movieapp.screens.movieDetail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.vi5hnu.movieapp.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(navController: NavController,id:String) {
    val movie=Movie.getMovie(id)
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "MovieDetail : ${movie?.title}",
                    color = MaterialTheme.colorScheme.onPrimary,
                    overflow = TextOverflow.Ellipsis)
                    },
            modifier = Modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
                rememberTopAppBarState()
            ),
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Go back to previous screen"
                    )
                }
            }
        )
    }){
            innerPadding ->
        MyApp(modifier = Modifier.padding(innerPadding)) {
            if(movie!=null) Column(modifier = Modifier
                .fillMaxSize()
                .padding(7.dp)) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(9 / 5f)
                        .clip(RoundedCornerShape(CornerSize(5.dp))),
                    model = movie.poster,
                    loading = {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    },
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.height(7.dp))
                LazyRow(modifier = Modifier){
                    itemsIndexed(movie.images){
                            index,imageUrl->
                        ElevatedCard(
                            modifier= Modifier
                                .width(250.dp)
                                .aspectRatio(6 / 4f),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp)
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(CornerSize(5.dp))),
                                model = imageUrl,
                                loading = {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                },
                                contentDescription = movie.title,
                                contentScale = ContentScale.Crop,
                            )
                        }
                        if(index!=movie.images.lastIndex) Spacer(modifier = Modifier.width(7.dp))
                    }
                }
                Divider(modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp))
                LazyColumn(modifier=Modifier.fillMaxSize()) {
                    item { MovieDetailItem("Title",movie.title) }
                    item { MovieDetailItem("Genre",movie.genre) }
                    item { MovieDetailItem("Plot",movie.plot) }
                    item { MovieDetailItem("Rating",movie.rating)  }
                }
            }
        }
    }
}

@Composable
private fun MovieDetailItem(title:String,value:String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
        Text(text = value, modifier = Modifier.weight(4f))
    }
}


@Composable
fun MyApp(modifier:Modifier=Modifier,content:@Composable ()->Unit) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        content()
    }
}
