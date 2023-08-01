package com.fedorov.andrii.andriiovych.imagesearch.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.HomeScreen
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.ImageSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearchTheme {
                    HomeScreen(onShareClicked = {url->
                        shareImage(url)
                    })
            }
        }
    }
    private fun shareImage(imageUrl:String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Image")
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            imageUrl
        )
        startActivity(Intent.createChooser(shareIntent, "Share Image"))
    }
}

