package com.polotechnologies.lindajamii.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.DeliveryDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*
import com.polotechnologies.lindajamii.dataModels.ExpectantSubsequentVisit

class FirestoreServiceViewModel : ViewModel() {
    val TAG = "FIRESTORE SERVICE VIEW MODEL"
    val firestoreService = FirestoreService()
    var patients = MutableLiveData<List<ExpectantDetails>>()

    var writeException = MutableLiveData<Exception>()


    fun getPatients(): LiveData<List<ExpectantDetails>> {
        firestoreService.getPatients().addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.d(TAG, "getPatients: $exception")
                patients.value = null
            }
            val patientsList: MutableList<ExpectantDetails> = mutableListOf()
            snapshot?.forEach { docSnapshot ->
                val patient = docSnapshot.toObject(ExpectantDetails::class.java)
                patientsList.add(patient)
            }
            patients.value = patientsList
        }
        return patients
    }

    fun saveSubsequentVisit(subsequentVisit: ExpectantSubsequentVisit) : LiveData<java.lang.Exception> {
        firestoreService.saveSubsequentVisit(subsequentVisit).addOnSuccessListener {
            writeException.value = null
        }.addOnFailureListener {exception->
            writeException.value = exception

        }

        return writeException
    }

    fun saveDeliveryDetails(deliveryDetails: DeliveryDetails) : LiveData<java.lang.Exception> {
        firestoreService.saveDeliveryDetails(deliveryDetails).addOnSuccessListener {
            writeException.value = null
        }.addOnFailureListener {exception->
            writeException.value = exception

        }
        return writeException
    }

    fun saveInitialVisitMaternalProfile(expectantDetails: ExpectantDetails) : LiveData<java.lang.Exception> {
        firestoreService.saveInitialVisitMaternalProfile(expectantDetails).addOnSuccessListener {
            writeException.value = null
        }.addOnFailureListener {exception->
            writeException.value = exception

        }
        return writeException
    }

    fun saveInitialVisitMedicalHistory(ancNumber : String, medicalSurgicalHistory: ExpectantMedicalSurgicalHistory) : LiveData<java.lang.Exception> {
        firestoreService.saveInitialVisitMedicalHistory(ancNumber, medicalSurgicalHistory).addOnSuccessListener {
            writeException.value = null
        }.addOnFailureListener {exception->
            writeException.value = exception

        }
        return writeException
    }

    fun saveInitialVisitPhysicalAntenatal(ancNumber : String, physicalAntenatalFeeding: ExpectantPhysicalAntenatalFeeding) : LiveData<java.lang.Exception> {
        firestoreService.saveInitialVisitPhysicalAntenatal(ancNumber, physicalAntenatalFeeding).addOnSuccessListener {
            writeException.value = null
        }.addOnFailureListener {exception->
            writeException.value = exception

        }
        return writeException
    }
}