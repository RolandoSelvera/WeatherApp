package com.rolandoselvera

import com.rolandoselvera.data.local.models.WeatherEntity
import com.rolandoselvera.data.local.repositories.WeatherDbRepository
import com.rolandoselvera.data.remote.models.responses.Condition
import com.rolandoselvera.data.remote.models.responses.Current
import com.rolandoselvera.data.remote.models.responses.Location
import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import com.rolandoselvera.data.remote.repositories.WeatherRepository
import com.rolandoselvera.utils.UiState
import com.rolandoselvera.viewmodels.home.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var repository: WeatherRepository
    private lateinit var repositoryDb: WeatherDbRepository

    // Test dispatcher for coroutine:
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        // Init mocks for API and database repositories:
        repository = mock(WeatherRepository::class.java)
        repositoryDb = mock(WeatherDbRepository::class.java)

        // Create the ViewModel and inject the mocked repositories:
        weatherViewModel = WeatherViewModel(repository, repositoryDb)
        Dispatchers.setMain(testDispatcher)
    }

    // SUCCESS CASE:
    @Test
    fun `test getWeather should update uiState to Success when weather fetched successfully`() =
        runTest {
            // Mock data for WeatherEntity that will be returned from the database:
            val mockWeatherEntity = WeatherEntity(
                condition = "Sunny",
                tempC = 25.0,
                locationName = "TestCity",
                locationRegion = "TestRegion",
                country = "TestCountry",
                urlIcon = "https://example.com/icon.png",
                localTime = "2024-12-29 10:00",
                windKph = 10.0,
                winDir = "Northeast",
                humidity = 50
            )

            // Mock response from the repository when fetching weather data (API):
            val mockWeatherResponse = listOf(
                WeatherResponse(
                    location = Location(
                        "TestCity",
                        "TestRegion",
                        "TestCountry",
                        0.0,
                        0.0,
                        "",
                        0,
                        "2024-12-29 10:00"
                    ),
                    current = Current(
                        lastUpdatedEpoch = 0,
                        lastUpdated = "",
                        tempC = 25.0,
                        tempF = 0.0,
                        isDay = 0,
                        condition = Condition("Sunny", "https://example.com/icon.png", 0),
                        windMph = 0.0,
                        windKph = 10.0,
                        windDegree = 0,
                        windDir = "Northeast",
                        pressureMb = 0.0,
                        pressureIn = 0.0,
                        precipMm = 0.0,
                        precipIn = 0.0,
                        humidity = 50,
                        cloud = 0,
                        feelslikeC = 0.0,
                        feelslikeF = 0.0,
                        windchillC = 0.0,
                        windchillF = 0.0,
                        heatindexC = 0.0,
                        heatindexF = 0.0,
                        dewpointC = 0.0,
                        dewpointF = 0.0,
                        visKm = 0.0,
                        visMiles = 0.0,
                        uv = 0.0,
                        gustMph = 0.0,
                        gustKph = 0.0
                    )
                )
            )

            // Mock the repository and database methods to return the mock data:
            `when`(repository.fetchWeather(any())).thenReturn(mockWeatherResponse[0])
            `when`(repositoryDb.saveWeatherIfNew(any())).thenReturn(Unit)
            `when`(repositoryDb.getAllWeather()).thenReturn(listOf(mockWeatherEntity))

            weatherViewModel.getWeather("TestCity")

            // Verify that the UI state is updated to success with the correct data:
            val result = weatherViewModel.uiState.value
            assertTrue(result is UiState.Success)
            assertEquals(
                mockWeatherResponse,
                (result as UiState.Success).data
            )

            println("Test passed: getWeather update uiState to Success correctly.")
        }

    // ERROR CASE:
    @Test
    fun `test loadLocalWeather should update uiState to Error when loading local weather fails`() =
        runTest {
            // Simulate an exception:
            val mockException = RuntimeException("Failed to load local weather data")

            // Database repository throws error:
            `when`(repositoryDb.getAllWeather()).thenThrow(mockException)

            weatherViewModel.loadLocalWeather()

            // Verify that the UI state is updated to error:
            val result = weatherViewModel.uiState.value
            assertTrue(result is UiState.Error)
            assertEquals(mockException, (result as UiState.Error).throwable)

            println("Test passed: loadLocalWeather handled the error correctly.")
        }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}