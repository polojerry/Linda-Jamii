package com.polotechnologies.lindajamii.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.DeliveryDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*

class FirestoreServiceViewModel : ViewModel() {
    val TAG = "FIRESTORE SERVICE VIEW MODEL"
    val firestoreService = FirestoreService()
    var writeException = MutableLiveData<Exception>()

    fun saveDeliveryDetails(deliveryDetails: DeliveryDetails): LiveData<java.lang.Exception> {
        firestoreService.saveDeliveryDetails(deliveryDetails).addOnSuccessListener {
            writeException.value = null
        }.addOnFailureListener { exception ->
            writeException.value = exception

        }
        return writeException
    }

    fun saveInitialVisitMaternalProfile(expectantDetails: ExpectantDetails): LiveData<java.lang.Exception> {
        firestoreService.saveInitialVisitMaternalProfile(expectantDetails).addOnSuccessListener {
            writeException.value = null
        }.addOnFailureListener { exception ->
            writeException.value = exception

        }
        return writeException
    }

    fun saveInitialVisitMedicalHistory(
        ancNumber: String,
        medicalSurgicalHistory: ExpectantMedicalSurgicalHistory
    ): LiveData<java.lang.Exception> {
        firestoreService.saveInitialVisitMedicalHistory(ancNumber, medicalSurgicalHistory)
            .addOnSuccessListener {
                writeException.value = null
            }.addOnFailureListener { exception ->
            writeException.value = exception

        }
        return writeException
    }

    fun saveInitialVisitPhysicalAntenatal(
        ancNumber: String,
        physicalAntenatalFeeding: ExpectantPhysicalAntenatalFeeding
    ): LiveData<java.lang.Exception> {
        firestoreService.saveInitialVisitPhysicalAntenatal(ancNumber, physicalAntenatalFeeding)
            .addOnSuccessListener {
                writeException.value = null
            }.addOnFailureListener { exception ->
            writeException.value = exception

        }
        return writeException
    }
}