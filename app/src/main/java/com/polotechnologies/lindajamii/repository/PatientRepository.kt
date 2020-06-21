package com.polotechnologies.lindajamii.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.asDomainModel
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.network.FirestoreService
import com.polotechnologies.lindajamii.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class PatientRepository(val database: LindaJamiiDatabase) {
    private val firestoreService = FirestoreService()

    /*suspend fun refreshPatients() {
        withContext(Dispatchers.IO) {
            firestoreService.getPatients()
            val patientsList = firestoreService.patients
            *//*database.expectantDetailsDao.insert(*patientsList.toTypedArray())*//*
        }
    }*/

    suspend fun refreshPatients() {
        withContext(Dispatchers.IO) {
            firestoreService.getAllPatients("").collect{resource->
                when(resource){
                    is Resource.Success ->{
                        database.expectantDetailsDao.insert(*resource.data.toTypedArray())
                    }
                }
            }
        }
    }

    val patients: LiveData<List<ExpectantDetails>> =
        Transformations.map(database.expectantDetailsDao.getPatients()) { expectantList ->
            expectantList.asDomainModel()
        }


}


