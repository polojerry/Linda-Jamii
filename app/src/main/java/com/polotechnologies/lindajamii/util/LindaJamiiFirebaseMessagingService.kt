package com.polotechnologies.lindajamii.util

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class LindaJamiiFirebaseMessagingService: FirebaseMessagingService() {
    companion object {
        private const val TAG = "LindaJamiiFirebaseMessagingService"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        var notificationTitle = ""
        var notificationBody = ""
        var notificationData = ""

        try {
            notificationTitle = message.notification?.title.toString()
            notificationBody = message.notification?.title.toString()
            notificationData = message.data.toString()

        }catch (exception : NullPointerException){
            Log.d(TAG, "onMessageReceived: Null Pointer Exception: ${exception.localizedMessage}")
        }

        Log.d(TAG, "onMessageReceived: Tittle: $notificationTitle")
        Log.d(TAG, "onMessageReceived: Body: $notificationBody")
        Log.d(TAG, "onMessageReceived: Data: $notificationData")
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }
}