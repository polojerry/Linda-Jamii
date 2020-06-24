package com.polotechnologies.lindajamii.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.DeliveryDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*
import com.polotechnologies.lindajamii.dataModels.ExpectantSubsequentVisit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class FirestoreService {
    private val mDatabase = FirebaseFirestore.getInstance()
    private val patientsCollectionReference = mDatabase.collection("patients")
        .document("maternalVisit").collection("initialVisit")

    //region Old Implementation
    val TAG = "FIRESTORE- SERVICE"

    var patients = listOf<ExpectantDetails?>()
    var writeException = MutableLiveData<Exception>()


    private fun subsequentReference(): DocumentReference {
        return mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("subsequentVisits").document()

    }

    fun saveSubsequentVisit(subsequentVisit: ExpectantSubsequentVisit): LiveData<java.lang.Exception> {
        subsequentReference().set(subsequentVisit).addOnSuccessListener {
            writeException.value = null
        }.addOnFailureListener { exception ->
            writeException.value = exception

        }
        return writeException
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
    //endregion

    fun getAllPatients(facilityId: String) = flow<Resource<List<ExpectantDetails>>> {
        //Loading
        emit(Resource.loading())

        val patientsSnapshot = patientsCollectionReference.get().await()
        val patientsList = patientsSnapshot.toObjects(ExpectantDetails::class.java)

        //Success
        emit(Resource.success(patientsList))

    }.catch {
        emit(Resource.failed(it.localizedMessage!!))
    }.flowOn(Dispatchers.IO)


}