package com.rafflypohan.mymoviecomposeapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rafflypohan.mymoviecomposeapp.R
import com.rafflypohan.mymoviecomposeapp.data.DataDummy
import com.rafflypohan.mymoviecomposeapp.data.Movie
import com.rafflypohan.mymoviecomposeapp.ui.detail.DetailActivity
import com.rafflypohan.mymoviecomposeapp.ui.theme.MyMovieComposeAppTheme
import com.rafflypohan.mymoviecomposeapp.utils.TextUtils
import com.rafflypohan.mymoviecomposeapp.utils.TextUtils.fixTextPadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMovieComposeAppTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                }

                Column {
                    Toolbar()
                    MainScreen()
                }

            }
        }
    }
}

@Composable
fun Toolbar() {
    TopAppBar(
        title = {
            Text(
                text = "Movie App",
                fontSize = 23.sp,
                modifier = Modifier.fillMaxWidth().padding(top = fixTextPadding(23).dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewToolbar() {
    MyMovieComposeAppTheme {
        Toolbar()
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(8.dp))
    val context = LocalContext.current
    val movies = DataDummy.movies
    LazyColumn(modifier = modifier) {
        items(
            items = movies,
        ) { movie ->
            MovieListItem(
                movie = movie,
                modifier = modifier.clickable {
                    val intent = Intent(context, DetailActivity::class.java)
                        .putExtra(DetailActivity.EXTRA_DATA, movie)
                    context.startActivity(intent)
                }
            )
        }
    }

}

@Composable
fun MovieListItem(movie: Movie, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(all = 20.dp)) {
        Image(
            painter = rememberImagePainter(data = movie.imagePath),
            contentDescription = "Movie Poster",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(width = 102.dp, height = 128.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column(modifier = Modifier.width(239.dp)) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h2,
                fontSize = 18.sp,
                softWrap = true,
                modifier = Modifier.fillMaxWidth(1f).padding(top = fixTextPadding(18).dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = movie.genre,
                fontSize = 14.sp,
                softWrap = true,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth(1f).padding(top = fixTextPadding(14).dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_star_24),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = movie.rating.toString(),
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(1f).padding(top = fixTextPadding(14).dp),
                    style = MaterialTheme.typography.body1
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MyMovieComposeAppTheme {
        MainScreen()
    }
}


