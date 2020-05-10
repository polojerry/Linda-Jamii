package com.polotechnologies.lindajamii.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.asDomainModel
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.polotechnologies.lindajamii.network.asDatabaseModel

class PatientRepository(val firestoreServiceViewModel: FirestoreServiceViewModel,val database: LindaJamiiDatabase) {

    val patients : LiveData<List<ExpectantDetails>> =
        Transformations.map(database.expectantDetailsDao.getPatients()){expectantList->
            expectantList.asDomainModel()
        }



    suspend fun refreshPatients(){
        withContext(Dispatchers.IO){
            firestoreServiceViewModel.getPatients()
            val patientsList = firestoreServiceViewModel.patients
            database.expectantDetailsDao.insert(*patientsList.toTypedArray())
        }
    }
}
