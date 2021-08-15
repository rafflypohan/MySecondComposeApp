package com.rafflypohan.mymoviecomposeapp.ui.detail

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rafflypohan.mymoviecomposeapp.R
import com.rafflypohan.mymoviecomposeapp.data.DataDummy
import com.rafflypohan.mymoviecomposeapp.data.Movie
import com.rafflypohan.mymoviecomposeapp.ui.theme.MyMovieComposeAppTheme
import com.rafflypohan.mymoviecomposeapp.utils.TextUtils

class DetailActivity : ComponentActivity() {
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

                val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
                Column {
                    DetailToolbar()
                    if (movie != null) {
                        DetailScreen(movie = movie)
                    }
                }

            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}

@Composable
fun DetailToolbar() {
    val context = LocalContext.current as Activity
    TopAppBar(
        title = {
            Text(
                text = "Detail",
                fontSize = 23.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2
            )

        },
        navigationIcon = {
            IconButton(onClick = { context.finish() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    )
}

@Composable
fun DetailScreen(movie: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
    ) {


        Image(
            painter = rememberImagePainter(data = movie.imagePath),
            contentDescription = "Movie Poster",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(width = 227.dp, height = 298.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = modifier.height(28.dp))

        Box(
            modifier = modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.onSurface)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {

            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_star_24),
                    contentDescription = null
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = movie.rating.toString(),
                    fontSize = 12.sp,
                    modifier = modifier.padding(top = TextUtils.fixTextPadding(22).dp),
                    style = MaterialTheme.typography.body1
                )
            }
        }

        Spacer(modifier = modifier.height(6.dp))

        Text(
            text = movie.title,
            fontSize = 20.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = TextUtils.fixTextPadding(20).dp),
            style = MaterialTheme.typography.h2
        )

        Spacer(modifier = modifier.height(2.dp))

        Text(
            text = movie.genre,
            fontSize = 14.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = TextUtils.fixTextPadding(14).dp),
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = modifier.height(4.dp))

        Row {
            Text(
                text = movie.releaseDate,
                fontSize = 14.sp,
                modifier = modifier.padding(top = TextUtils.fixTextPadding(14).dp),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = modifier.width(8.dp))

            Box(
                modifier = modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.surface)
                    .align(alignment = Alignment.CenterVertically)
            )

            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = movie.duration,
                fontSize = 14.sp,
                modifier = modifier.padding(top = TextUtils.fixTextPadding(14).dp),
                style = MaterialTheme.typography.body1
            )
        }

        Spacer(modifier = modifier.height(18.dp))

        Text(
            text = "Overview",
            fontSize = 18.sp,
            modifier = modifier.padding(top = TextUtils.fixTextPadding(18).dp),
            style = MaterialTheme.typography.h2
        )



        Text(
            text = movie.overview,
            fontSize = 14.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = TextUtils.fixTextPadding(14).dp),
            style = MaterialTheme.typography.body1
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    MyMovieComposeAppTheme {
        DetailScreen(movie = DataDummy.movies[0])
    }
}

