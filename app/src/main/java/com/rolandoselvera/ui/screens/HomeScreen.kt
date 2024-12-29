package com.rolandoselvera.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rolandoselvera.R
import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import com.rolandoselvera.ui.components.MainButton
import com.rolandoselvera.ui.components.SearchTextField
import com.rolandoselvera.ui.components.MainTitle
import com.rolandoselvera.ui.components.SimpleDialog
import com.rolandoselvera.ui.components.SpaceView
import com.rolandoselvera.ui.components.WeatherItem
import com.rolandoselvera.ui.theme.MyApplicationTheme
import com.rolandoselvera.ui.theme.Primary
import com.rolandoselvera.ui.theme.Secondary
import com.rolandoselvera.ui.widgets.ProgressDialog
import com.rolandoselvera.utils.UiState
import com.rolandoselvera.viewmodels.home.WeatherViewModel


@Composable
fun HomeScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isShowDialog = remember { mutableStateOf(true) }
    var searchTerm by remember { mutableStateOf("") }
    val weatherResults = remember { mutableStateListOf<WeatherResponse>() }

    LaunchedEffect(Unit) {
        viewModel.getWeather("Matamoros")
    }

    LayoutHomeScreen(
        viewModel = viewModel,
        searchTerm = searchTerm,
        onNameChange = { searchTerm = it },
        weatherResults = weatherResults
    )

    when (uiState) {
        UiState.Loading -> {
            isShowDialog.value = true
        }

        is UiState.Error -> {
            if (isShowDialog.value) {
                SimpleDialog(
                    isVisible = isShowDialog.value,
                    title = stringResource(R.string.app_name),
                    message = stringResource(
                        R.string.error_message,
                        "${(uiState as UiState.Error).throwable.message}"
                    ),
                    buttonText = stringResource(R.string.accept),
                    onDismiss = {
                        isShowDialog.value = false
                    }
                )
            }
        }

        is UiState.Success -> {
            val weather = (uiState as UiState.Success<WeatherResponse>).data
            if (!weatherResults.contains(weather)) {
                weatherResults.add(weather)
            }
        }
    }

    if (uiState is UiState.Loading) {
        ProgressDialog(isVisible = true)
    } else {
        ProgressDialog(isVisible = false)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutHomeScreen(
    viewModel: WeatherViewModel,
    searchTerm: String,
    onNameChange: (String) -> Unit,
    weatherResults: List<WeatherResponse>
) {
    Scaffold(
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
            )
        }
    ) { paddingValues ->
        ContentHomeScreen(
            viewModel = viewModel,
            searchTerm = searchTerm,
            onNameChange = onNameChange,
            weatherResults = weatherResults,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun ContentHomeScreen(
    viewModel: WeatherViewModel,
    searchTerm: String,
    onNameChange: (String) -> Unit,
    weatherResults: List<WeatherResponse>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchTerm,
                onValueChange = onNameChange,
                label = stringResource(R.string.search_location),
                onClickSearchIcon = {
                    viewModel.getWeather(searchTerm)
                }
            )

            SpaceView(padding = 8.dp)

            MainButton(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = null,
                text = stringResource(R.string.search),
                enabled = true,
                onClick = {
                    viewModel.getWeather(searchTerm)
                }
            )

            MainButton(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = null,
                backgroundColor = Secondary,
                text = stringResource(R.string.save),
                enabled = true,
                onClick = {

                }
            )

            SpaceView(padding = 8.dp)

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(weatherResults) { weather ->
                    WeatherItem(weather = weather)
                    HorizontalDivider(thickness = 2.dp)
                }
            }
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
    val viewModel: WeatherViewModel = hiltViewModel()
    MyApplicationTheme {
        LayoutHomeScreen(viewModel, "", {}, arrayListOf())
    }
}