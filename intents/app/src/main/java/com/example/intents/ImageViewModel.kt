package com.example.intents

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ImageViewModel: ViewModel() {

    var imageUri: Uri? by mutableStateOf(null)
        private set

    fun setUri(uri: Uri?) {
        imageUri = uri
    }

}