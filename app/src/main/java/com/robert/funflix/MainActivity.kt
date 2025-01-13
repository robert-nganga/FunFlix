package com.robert.funflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.robert.funflix.core.data.BASE_URL
import com.robert.funflix.ui.theme.FunFlixTheme
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    private val httpClient: HttpClient by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                FunFlixTheme {
                    var isLoading by remember { mutableStateOf(false) }
                    var movies by remember { mutableStateOf("") }
                    val scope = rememberCoroutineScope()
                    Scaffold(
                        modifier =
                        Modifier
                            .fillMaxSize(),
                    ) { innerPadding ->
                        Column(
                            modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        isLoading = true
                                        try {
                                            val result = httpClient.get("$BASE_URL/movie/popular?language=en-US&page=1")
                                            movies = result.bodyAsText()
                                            isLoading = false
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                            isLoading = false
                                        }
                                    }
                                },
                            ) {
                                Text("Get movies")
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier =
                                Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                if (isLoading) {
                                    CircularProgressIndicator()
                                } else {
                                    Text(movies)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
