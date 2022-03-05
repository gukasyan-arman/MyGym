package com.example.mygym

import androidx.appcompat.app.AppCompatActivity

const val NUMBER_OF_PAGER_ITEM = 3
const val GYM_EMAIL = "gukasian.a.a@gmail.com"
const val GYM_INST = "https://www.instagram.com/armanigukas/"
const val GYM_WEBSITE = "https://www.goldsgym.ru/"
const val GYM_VK = "https://vk.com/armangukas"
const val GYM_SHARE_TEXT = "https://www.goldsgym.ru/\nНазвание зала\nАдрес зала\nОписание зала"

fun showActionBar(activity: AppCompatActivity) {
    activity.supportActionBar!!.show()
}

fun hideActionBar(activity: AppCompatActivity) {
    activity.supportActionBar!!.hide()
}