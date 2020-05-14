package com.polotechnologies.lindajamii.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.asDomainModel
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.network.FirestoreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PatientRepository(val database: LindaJamiiDatabase) {
    private val firestoreService = FirestoreService()

    suspend fun refreshPatients() {
        withContext(Dispatchers.IO) {
            firestoreService.getPatients()
            val patientsList = firestoreService.patients
            database.expectantDetailsDao.insert(*patientsList.toTypedArray())
        }
    }

    val patients: LiveData<List<ExpectantDetails>> =
        Transformations.map(database.expectantDetailsDao.getPatients()) { expectantList ->
            expectantList.asDomainModel()


        }
}
