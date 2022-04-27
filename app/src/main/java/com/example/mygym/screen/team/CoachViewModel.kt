package com.example.mygym.screen.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygym.model.Coach

open class CoachViewModel: ViewModel() {

    val coachId: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val coachFirstName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val coachLastName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val coachPost: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val coachDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val fullName = "$coachFirstName $coachLastName"

}