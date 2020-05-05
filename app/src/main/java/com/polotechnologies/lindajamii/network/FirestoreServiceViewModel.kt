package com.polotechnologies.lindajamii.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails

class FirestoreServiceViewModel :ViewModel() {
    val TAG = "FIRESTORE SERVICE VIEW MODEL"
    val firestoreService = FirestoreService()
    var patients  =  MutableLiveData<List<ExpectantDetails>>()


    fun getPatients(): LiveData<List<ExpectantDetails>>{
        firestoreService.getPatients().addSnapshotListener { snapshot, exception ->
            if(exception !=null){
                Log.d(TAG, "getPatients: $exception")
                patients.value = null
            }

            val patientsList : MutableList<ExpectantDetails> = mutableListOf()
            snapshot?.forEach {docSnapshot->
                val patient = docSnapshot.toObject(ExpectantDetails::class.java)
                patientsList.add(patient)
            }
            patients.value= patientsList

        }

        return patients
    }
}