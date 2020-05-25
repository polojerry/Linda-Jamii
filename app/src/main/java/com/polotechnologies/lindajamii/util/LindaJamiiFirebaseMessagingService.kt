package com.polotechnologies.lindajamii.util

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class LindaJamiiFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        private const val TAG = "LindaJamiiFirebaseMessagingService"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        var patientName = ""
        var visitDate = ""
        var ancNumber = ""

        try {
            patientName = message.data["patientName"].toString()
            visitDate = message.data["patientNextVisit"].toString()
            ancNumber = message.data["patientAncNumber"].toString()

        } catch (exception: NullPointerException) {
            Log.d(TAG, "onMessageReceived: Null Pointer Exception: ${exception.localizedMessage}")
        }

        Log.d(TAG, "onMessageReceived: Name: $patientName")
        Log.d(TAG, "onMessageReceived: Visit Date: $visitDate")
        Log.d(TAG, "onMessageReceived: AncNumber: $ancNumber")


        ExpectantVisitNotification.notify(baseContext, patientName, visitDate, ancNumber)
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    override fun onNewToken(newToken: String) {
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