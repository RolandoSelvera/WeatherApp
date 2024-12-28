package com.rolandoselvera.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rolandoselvera.R
import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import com.rolandoselvera.ui.components.HeaderIconButton
import com.rolandoselvera.ui.components.MainButton
import com.rolandoselvera.ui.components.MainTextField
import com.rolandoselvera.ui.components.MainTitle
import com.rolandoselvera.ui.components.SpaceView
import com.rolandoselvera.ui.theme.MyApplicationTheme
import com.rolandoselvera.ui.theme.Primary
import com.rolandoselvera.utils.UiState
import com.rolandoselvera.viewmodels.home.WeatherViewModel


@Composable
fun HomeScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getWeather("Matamoros")
    }

    when (uiState) {
        is UiState.Loading -> {
            Text(stringResource(R.string.loading_message), modifier = Modifier.fillMaxSize())
        }

        is UiState.Error -> {
            Text(
                text = stringResource(
                    R.string.error_message,
                    "${(uiState as UiState.Error).throwable.message}"
                ),
                modifier = Modifier.fillMaxSize(),
                color = Color.Red
            )
        }

        is UiState.Success -> {
            val weather = (uiState as UiState.Success<WeatherResponse>).data
            LayoutHomeScreen(weather)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutHomeScreen(weather: WeatherResponse?) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    MainTitle(
                        modifier = Modifier.fillMaxWidth(),
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

                    }
                }
            )
        }
    ) { paddingValues ->
        ContentHomeScreen(
            weather,
            {},
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun ContentHomeScreen(
    weather: WeatherResponse?,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var nameDataItemType by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MainTextField(
                modifier = Modifier.fillMaxWidth(),
                nameDataItemType,
                { nameDataItemType = it },
                stringResource(R.string.search)
            )

            SpaceView(padding = 8.dp)

            MainButton(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = null,
                text = stringResource(R.string.save),
                enabled = true
            ) { }

            SpaceView(padding = 8.dp)

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
    }
}

@Preview(
    showBackground = true,
    device = "spec:parent=pixel_8," +
            "navigation=buttons",
    showSystemUi = true
)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        LayoutHomeScreen(null)
    }
}