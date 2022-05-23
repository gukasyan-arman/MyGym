package com.example.mygym.notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class PushService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG", "Refreshed token: $token")


    }

}