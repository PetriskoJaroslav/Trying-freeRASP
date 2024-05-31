package com.example.tryingfreerasp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tryingfreerasp.ui.theme.TryingFreeRASPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryingFreeRASPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (SafeApplication.safeInstallation.value){
                        MainScreen()
                    } else {
                        WarningScreen(SafeApplication.warningString)
                    }
                }
            }
        }
    }
}
object SafeApplication{
    var safeInstallation: MutableState<Boolean> = mutableStateOf(true)
    var warningString = -1;
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier.wrapContentSize(Alignment.Center)) {
        Text(
            text = "Hello kind person from Talsec!",
            modifier = modifier
        )
    }
}

@Composable
fun WarningScreen(warning: Int, modifier: Modifier = Modifier) {
    Box(
        modifier
            .wrapContentSize(Alignment.Center)
            .background(color = Color.Red)) {
        Text(
            text = stringResource(id = warning),
            modifier = modifier
        )
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TryingFreeRASPTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun WarningScreenPreview() {
    TryingFreeRASPTheme {
        WarningScreen(R.string.dummy_warning)
    }
}