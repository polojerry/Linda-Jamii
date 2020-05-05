package com.polotechnologies.lindajamii.network

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService {
    val TAG = "FIRESTORE- SERVICE"
    val mDatabase = FirebaseFirestore.getInstance()

    fun getPatients() : CollectionReference{
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("initialVisit")
    }
}