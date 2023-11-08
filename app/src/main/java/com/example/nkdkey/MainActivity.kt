package com.example.nkdkey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nkdkey.ui.theme.NKDKeyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        encryptInitial()
        setContent {
            NKDKeyTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        Greeting(Native().calculateSquare(7, 5).toString())
//                        Greeting(AppConstants.MAP_KEY)
                        Greeting(Native().getMapsKey())
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NKDKeyTheme {
        Greeting("Android")
    }
}


fun encryptInitial() {
    Encryptor.encryptInitial()

    MapsInitializer()

}

class MapsInitializer {
    init {
        initializeMaps(Encryptor.getMapsKey())
    }
}

fun initializeMaps(apiKey: String) {

}