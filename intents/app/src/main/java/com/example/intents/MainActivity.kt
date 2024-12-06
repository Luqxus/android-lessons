package com.example.intents

import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.intents.ui.theme.IntentsTheme

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        viewModel.imageUri?.let {
                            AsyncImage(model = viewModel.imageUri, contentDescription = null)
                        }


                        Button(
                            onClick = {

//                                Intent(Intent.ACTION_MAIN). also {
//                                    it.`package` = "com.google.android.youtube"

//                                    try {
//                                        startActivity(it)
//                                    } catch (e: ActivityNotFoundException) {
//                                        e.printStackTrace()
//                                    }

//                                    // check if app is installed on device
//                                    if(it.resolveActivity(packageManager) != null) {
//
//                                    }


                                // Accepting intents from other apps
                                    val intent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_EMAIL, arrayOf("lebelwane@protonmail.com"))
                                        putExtra(Intent.EXTRA_SUBJECT, arrayOf("Intent Subject"))
                                        putExtra(Intent.EXTRA_TEXT, arrayOf("Intent email  content"))

                                    }

                                    if(intent.resolveActivity(packageManager) != null) {
                                        startActivity(intent)
                                    }

                            }
                        ) {
                            Text("Launch Intent")
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        var uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }

        viewModel.setUri(uri)
    }
}