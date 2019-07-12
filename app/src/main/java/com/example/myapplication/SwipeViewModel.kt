package com.example.myapplication

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SwipeViewModel: ViewModel() {

    private val stream = MutableLiveData<SwipeModel>()

    val modelStream: LiveData<SwipeModel>
        get() = stream

    private val data = listOf(
        CardModel(Color.parseColor("#74b25d"), 44),
        CardModel(Color.parseColor("#a3efff"), 23),
        CardModel(Color.parseColor("#ffcf00"), 123),
        CardModel(Color.parseColor("#28652c"), 453),
        CardModel(Color.parseColor("#14a8c7"), 51)
    )
    private var currentIndex = 0

    private val topCard
        get() = data[currentIndex % data.size]
    private val bottomCard
        get() = data[(currentIndex + 1) % data.size]

    init {
        updateStream()
    }

    fun swipe() {
        currentIndex += 1
        updateStream()
    }

    private fun updateStream() {
        stream.value = SwipeModel(topCard, bottomCard)
    }

}