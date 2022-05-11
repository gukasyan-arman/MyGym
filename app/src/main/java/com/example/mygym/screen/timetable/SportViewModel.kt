package com.example.mygym.screen.timetable

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SportViewModel: ViewModel() {

    val sportTitle: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val sportDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val sportRoom: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val sportTrainer: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val sportTime: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val sportDuration: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val sportMembersCurrent: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val sportMembersMax: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

}