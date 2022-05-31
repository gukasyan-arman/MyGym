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

    val sportDuration: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }

    val sportMembersCurrent: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }

    val sportMembersMax: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }

    val date: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val sportId: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}