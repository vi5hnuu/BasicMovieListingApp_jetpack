package com.vi5hnu.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.vi5hnu.movieapp.model.Movie

@Composable
fun Movie(movie: Movie, onClick:(movie:String)->Unit={}){
    val expandedState= remember {
        mutableStateOf(false)
    };

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 3.dp)
            .clip(RoundedCornerShape(CornerSize(5.dp)))
            .clickable { onClick(movie.id) },
        shape = RoundedCornerShape(CornerSize(5.dp)),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 0.5.dp
        )
    ) {
        Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)) {
                Column {
                    Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .weight(3f)
                                .aspectRatio(5 / 3.5f, true)
                                .clip(RoundedCornerShape(CornerSize(5.dp))),
                            model = movie.poster,
                            loading = {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            },
                            contentDescription = movie.title,
                            contentScale = ContentScale.Crop,
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Column(modifier = Modifier
                            .weight(4f)
                            .fillMaxHeight()) {
                            Text(text = movie.title,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp)
                            Row {
                                Text(text = "Director : ")
                                Text(text = movie.director)
                            }
                            Row {
                                Text(text = "Released : ")
                                Text(text = movie.year)
                            }
                            Box(modifier = Modifier.align(Alignment.CenterHorizontally)){
                                IconButton(onClick = { expandedState.value=!expandedState.value;},
                                    modifier = Modifier) {
                                    Icon(imageVector = if(expandedState.value) Icons.Sharp.KeyboardArrowUp else Icons.Sharp.KeyboardArrowDown, contentDescription = "expand arrow")
                                }
                            }
                        }
                    }
                    AnimatedVisibility(visible = expandedState.value) {
                       Column {
                           Text(text=buildAnnotatedString {
                               withStyle(style = SpanStyle(color= Color.Black,
                                   fontSize = 18.sp,
                                   fontWeight = FontWeight.Medium)){
                                   append("Plot : ")
                               }
                               withStyle(style = SpanStyle(
                                   fontSize = 16.sp)){
                                   append(movie.plot)
                               }
                           }, style = TextStyle(textAlign = TextAlign.Justify))
                           Divider()
                           Text(text=buildAnnotatedString {
                               withStyle(style = SpanStyle(color= Color.Black,
                                   fontSize = 18.sp,
                                   fontWeight = FontWeight.Medium)){
                                   append("Director : ")
                               }
                               withStyle(style = SpanStyle(
                                   fontSize = 16.sp)){
                                   append(movie.director)
                               }
                           }, style = TextStyle(textAlign = TextAlign.Justify))
                           Divider()
                           Text(text=buildAnnotatedString {
                               withStyle(style = SpanStyle(color= Color.Black,
                                   fontSize = 18.sp,
                                   fontWeight = FontWeight.Medium)){
                                   append("Actor : ")
                               }
                               withStyle(style = SpanStyle(
                                   fontSize = 16.sp)){
                                   append(movie.actors)
                               }
                           }, style = TextStyle(textAlign = TextAlign.Justify))
                           Divider()
                           Text(text=buildAnnotatedString {
                               withStyle(style = SpanStyle(color= Color.Black,
                                   fontSize = 18.sp,
                                   fontWeight = FontWeight.Medium)){
                                   append("Rating : ")
                               }
                               withStyle(style = SpanStyle(
                                   fontSize = 16.sp)){
                                   append(movie.rating)
                               }
                           }, style = TextStyle(textAlign = TextAlign.Justify))
                       }
                    }
                }
        }
    }
}