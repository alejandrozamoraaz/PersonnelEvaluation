package com.example.evaluacionpersonalpruebaandroid.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is location Fragment"
    }
    val text: LiveData<String> = _text
}