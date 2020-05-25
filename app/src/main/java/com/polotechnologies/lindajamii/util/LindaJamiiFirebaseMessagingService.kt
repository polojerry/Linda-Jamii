package com.polotechnologies.lindajamii.util

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class LindaJamiiFirebaseMessagingService: FirebaseMessagingService() {
    companion object {
        private const val TAG = "LindaJamiiFirebaseMessagingService"
    }

    var newFcmToken = ""
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

    override fun onNewToken(newToken: String) {
        Log.d(TAG, "onNewToken: $newToken")
        newFcmToken = newToken
        sendTokenToFirestore(newToken)
    }

    private fun sendTokenToFirestore(newToken: String) {
        Log.d(TAG, "onNewToken: Sending Token to Firebase:::: $newToken")

        val tokenMap = HashMap<String, String>()
        tokenMap["token"] = newToken

        FirebaseFirestore.getInstance()
            .collection("users")
            .document("testUser")
            .set(tokenMap)
    }
}