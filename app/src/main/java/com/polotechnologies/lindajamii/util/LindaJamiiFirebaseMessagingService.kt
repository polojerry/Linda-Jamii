package com.polotechnologies.lindajamii.util

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class LindaJamiiFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        private const val TAG = "FMS"
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
            Log.d(TAG, "onMessageReceived:${exception.localizedMessage}")
        }


        val patientVisitDate = DateConverter.getStringDate(visitDate.toLong())
        ExpectantVisitNotification.notify(baseContext, patientName, patientVisitDate, ancNumber)
    }
    override fun onNewToken(newToken: String) {
        sendTokenToFirestore(newToken)
    }

    private fun sendTokenToFirestore(newToken: String) {
        val tokenMap = HashMap<String, String>()
        tokenMap["token"] = newToken

        FirebaseFirestore.getInstance()
            .collection("users")
            .document("testUser")
            .set(tokenMap)
    }
}