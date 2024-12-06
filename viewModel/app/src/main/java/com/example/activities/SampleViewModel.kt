package com.example.activities

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SampleViewModel: ViewModel() {

    var backgroundColor by mutableStateOf(Color.White)
        private set

    fun changeBackgroundColor() {
        backgroundColor = Color.Red
    }
}