package com.polotechnologies.lindajamii.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
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

    private val patientsDocumentReference = mDatabase.collection("patients")
        .document("maternalVisit")

    private val patientsInitialVisitCollectionReference = patientsDocumentReference
        .collection("initialVisit")

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

    //endregion


    fun getAllPatients(facilityId: String) = flow<Resource<List<ExpectantDetails>>> {
        //Loading
        emit(Resource.loading())

        val patientsSnapshot = patientsInitialVisitCollectionReference.get().await()
        val patientsList = patientsSnapshot.toObjects(ExpectantDetails::class.java)

        //Success
        emit(Resource.success(patientsList))

    }.catch {
        emit(Resource.failed(it.localizedMessage!!))
    }.flowOn(Dispatchers.IO)


    fun saveInitialVisitMaternalProfile(expectantDetails: ExpectantDetails)
            = flow<Resource<Boolean>> {

        emit(Resource.loading())

        val profileReference = patientsInitialVisitCollectionReference
            .document(expectantDetails.maternalProfile!!.ancNumber)
            .set(expectantDetails).await()

        emit(Resource.success(true))
    }.catch { exception ->
        emit(Resource.failed(exception.localizedMessage!!))
    }.flowOn(Dispatchers.IO)



    fun saveInitialVisitMedicalHistory(ancNumber: String,
                                       medicalSurgicalHistory: ExpectantMedicalSurgicalHistory)
            = flow<Resource<Boolean>> {

        emit(Resource.loading())

        val profileReference = patientsInitialVisitCollectionReference
            .document(ancNumber).update(
                "medicalSurgicalHistory", medicalSurgicalHistory)
            .await()

        emit(Resource.success(true))
    }.catch { exception ->
        emit(Resource.failed(exception.localizedMessage!!))
    }.flowOn(Dispatchers.IO)



    fun saveInitialVisitPhysicalAntenatal(
        ancNumber: String,
        physicalAntenatalFeeding: ExpectantPhysicalAntenatalFeeding)
            = flow<Resource<Boolean>> {

        emit(Resource.loading())

        val profileReference = patientsInitialVisitCollectionReference
            .document(ancNumber).update(
                "physicalAntenatalFeeding", physicalAntenatalFeeding)
            .await()

        emit(Resource.success(true))
    }.catch { exception ->
        emit(Resource.failed(exception.localizedMessage!!))
    }.flowOn(Dispatchers.IO)


}