package com.polotechnologies.lindajamii.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.polotechnologies.lindajamii.dataModels.DeliveryDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantSubsequentVisit
import com.polotechnologies.lindajamii.dataModels.asDomainModel
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.network.FirestoreService
import com.polotechnologies.lindajamii.network.Resource
import kotlinx.coroutines.flow.collect

class PatientRepository(val database: LindaJamiiDatabase) {
    private val firestoreService = FirestoreService()

    /**fetch patients live data from db*/
    val patients: LiveData<List<ExpectantDetails>> =
        Transformations.map(database.expectantDetailsDao.getPatients()) { expectantList ->
            expectantList.asDomainModel()
        }

    /**get all patients and post to db*/
    suspend fun refreshPatients(facilityId: String) {
        firestoreService.getAllPatients(facilityId).collect { resource ->
            if (resource is Resource.Success) {

                database.expectantDetailsDao.insert(*resource.data.toTypedArray())

            }

        }
    }

    /** Save Maternal Profile*/
    fun saveInitialVisitMaternalProfile(expectantDetails: ExpectantDetails) =
        firestoreService.saveInitialVisitMaternalProfile(expectantDetails)

    /** Save  Medical Surgical History*/
    fun saveInitialVisitMedicalHistory(
        ancNumber: String,
        medicalSurgicalHistory: ExpectantDetails.ExpectantMedicalSurgicalHistory
    ) =
        firestoreService.saveInitialVisitMedicalHistory(
            ancNumber,
            medicalSurgicalHistory
        )

    /** Save Subsequent Visit*/
    fun saveSubsequentVisit(
        expectantSubsequentVisit: ExpectantSubsequentVisit) =
        firestoreService.saveSubsequentVisit(expectantSubsequentVisit)

    /** Save Delivery Details*/
    fun saveDeliveryDetails(
        deliveryDetails: DeliveryDetails) =
        firestoreService.saveDeliveryDetails(deliveryDetails)


}


