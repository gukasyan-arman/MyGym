package com.example.mygym.screen.timetable

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DateViewModel: ViewModel() {
    val date: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}