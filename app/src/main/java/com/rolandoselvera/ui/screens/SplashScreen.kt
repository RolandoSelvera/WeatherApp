package com.rolandoselvera.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rolandoselvera.R
import com.rolandoselvera.ui.components.MainTitle
import com.rolandoselvera.ui.theme.MyApplicationTheme
import com.rolandoselvera.ui.theme.Primary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        navigateToHome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo"
        )
        MainTitle(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.app_name),
            fontSize = 24.sp
        )
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
        SplashScreen { }
    }
}