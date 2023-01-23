package com.example.marvelproject.notification

import android.annotation.SuppressLint
import android.content.Intent
import com.example.marvelproject.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class PushService: FirebaseMessagingService() {
    override fun onNewToken(newToken:String){
        super.onNewToken(newToken)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", message.notification?.body ?: "")
    }

}

