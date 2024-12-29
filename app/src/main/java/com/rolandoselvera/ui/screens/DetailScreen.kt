package com.rolandoselvera.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import com.rolandoselvera.R
import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import com.rolandoselvera.ui.components.HeaderIconButton
import com.rolandoselvera.ui.components.MainTitle
import com.rolandoselvera.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    weather: WeatherResponse?
) {
    Scaffold(
        // Header:
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    MainTitle(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        title = stringResource(R.string.app_name),
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary
                ),
                navigationIcon = {
                    HeaderIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { paddingValues ->
        // Body:
        ContentDetailScreen(
            modifier = Modifier.padding(paddingValues),
            weather = weather
        )
    }
}

@Composable
fun ContentDetailScreen(modifier: Modifier, weather: WeatherResponse?) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val urlIcon = weather?.current?.condition?.icon
        val model = ImageRequest.Builder(LocalContext.current)
            .data(urlIcon)
            .crossfade(true)
            .scale(Scale.FIT)
            .build()

        AsyncImage(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clip(shape = CircleShape),
            model = model,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_not_found),
            placeholder = painterResource(R.drawable.ic_not_found),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(
                        R.string.location,
                        "${weather?.location?.name}, ${weather?.location?.region}"
                    ),
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(
                        R.string.country,
                        "${weather?.location?.country}"
                    ),
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(
                        R.string.temperature,
                        "${weather?.current?.tempC}"
                    ),
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(
                        R.string.condition,
                        "${weather?.current?.condition?.text}"
                    ),
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(
                        R.string.localtime,
                        "${weather?.location?.localtime}"
                    ),
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(
                        R.string.wind,
                        "${weather?.current?.windKph}"
                    ),
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(
                        R.string.wind_direction,
                        "${weather?.current?.windKph}"
                    ),
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(
                        R.string.humidity,
                        "${weather?.current?.humidity}"
                    ),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true, showBackground = true,
    device = "spec:parent=pixel_8,navigation=buttons"
)
@Composable
fun DetailScreenPreview() {
    val navController = rememberNavController()
    DetailScreen(navController, null)
}