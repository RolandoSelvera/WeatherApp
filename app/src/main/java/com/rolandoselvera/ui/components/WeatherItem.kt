package com.rolandoselvera.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import com.rolandoselvera.R
import com.rolandoselvera.data.remote.models.responses.WeatherResponse

@Composable
fun WeatherItem(weather: WeatherResponse?, onDeleteClick: (WeatherResponse) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {

        val urlIcon = weather?.current?.condition?.icon
        val model = ImageRequest.Builder(LocalContext.current)
            .data(urlIcon)
            .crossfade(true)
            .scale(Scale.FIT)
            .build()

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clip(shape = CircleShape),
                model = model,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_not_found),
                placeholder = painterResource(R.drawable.ic_not_found),
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f) // Deja que este espacio use el máximo disponible
            ) {
                Text(
                    text = stringResource(
                        R.string.location,
                        "${weather?.location?.name}, ${weather?.location?.region}"
                    )
                )

                Text(
                    text = stringResource(
                        R.string.temperature,
                        "${weather?.current?.tempC}"
                    )
                )

                Text(
                    text = stringResource(
                        R.string.condition,
                        "${weather?.current?.condition?.text}"
                    )
                )
            }

            Image(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = "",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        weather?.location?.let { onDeleteClick(weather) }
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherItemPreview() {
    WeatherItem(null, {})
}