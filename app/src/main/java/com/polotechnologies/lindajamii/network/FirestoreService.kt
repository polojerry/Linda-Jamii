package com.polotechnologies.lindajamii.network

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.DeliveryDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*
import com.polotechnologies.lindajamii.dataModels.ExpectantSubsequentVisit

class FirestoreService {
    val TAG = "FIRESTORE- SERVICE"
    val mDatabase = FirebaseFirestore.getInstance()

    var patients = listOf<ExpectantDetails>()

    private fun patienceReference(): CollectionReference {
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("initialVisit")
    }

    fun getPatients() {
        patienceReference().addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.d(TAG, "getPatients: $exception")
                patients = emptyList()
            }
            val patientsList: MutableList<ExpectantDetails> = mutableListOf()
            snapshot?.forEach { docSnapshot ->
                val patient = docSnapshot.toObject(ExpectantDetails::class.java)
                patientsList.add(patient)
            }
            patients = patientsList
        }
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

    fun saveInitialVisitMaternalProfile(expectantDetails: ExpectantDetails): Task<Void> {
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("initialVisit").document(expectantDetails.maternalProfile!!.ancNumber)
            .set(
                expectantDetails
            )
    }

    fun saveInitialVisitMedicalHistory(
        ancNumber: String,
        medicalSurgicalHistory: ExpectantMedicalSurgicalHistory
    ): Task<Void> {
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("initialVisit").document(ancNumber).update(
                "medicalSurgicalHistory", medicalSurgicalHistory
            )
    }

    fun saveInitialVisitPhysicalAntenatal(
        ancNumber: String,
        physicalAntenatalFeeding: ExpectantPhysicalAntenatalFeeding
    ): Task<Void> {
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("initialVisit").document(ancNumber).update(
                "physicalAntenatalFeeding", physicalAntenatalFeeding
            )
    }

}