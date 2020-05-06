package com.polotechnologies.lindajamii.network

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.DeliveryDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantSubsequentVisit

class FirestoreService {
    val TAG = "FIRESTORE- SERVICE"
    val mDatabase = FirebaseFirestore.getInstance()

    fun getPatients(): CollectionReference {
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("initialVisit")
    }

    fun saveSubsequentVisit(subsequentVisit: ExpectantSubsequentVisit): Task<Void> {
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("subsequentVisits").document(subsequentVisit.registrationNumber).set(
                subsequentVisit
            )

    }

    fun saveDeliveryDetails(deliveryDetails: DeliveryDetails): Task<Void> {
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("deliveryDetails").document(deliveryDetails.registrationNumber).set(
                deliveryDetails
            )
    }

}