package com.example.mygym

import androidx.appcompat.app.AppCompatActivity

const val NUMBER_OF_PAGER_ITEM = 3
const val GYM_EMAIL = "gukasian.a.a@gmail.com"

fun showActionBar(activity: AppCompatActivity) {
    activity.supportActionBar!!.show()
}

fun hideActionBar(activity: AppCompatActivity) {
    activity.supportActionBar!!.hide()
}