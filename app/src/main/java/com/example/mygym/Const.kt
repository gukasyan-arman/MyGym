package com.example.mygym

import androidx.appcompat.app.AppCompatActivity

const val NUMBER_OF_PAGER_ITEM = 3

fun showActionBar(activity: AppCompatActivity) {
    activity.supportActionBar!!.show()
}

fun hideActionBar(activity: AppCompatActivity) {
    activity.supportActionBar!!.hide()
}