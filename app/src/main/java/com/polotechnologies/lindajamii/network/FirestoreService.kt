package com.polotechnologies.lindajamii.network

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

    private val patientsSubsequentVisitCollectionReference = patientsDocumentReference
        .collection("subsequentVisits")

    private val patientsDeliveryCollectionReference = patientsDocumentReference
        .collection("delivery")


    /**Fetching all patients*/
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


    //region Patients Initial Visit
    /**Post Maternal Profile*/
    fun saveInitialVisitMaternalProfile(expectantDetails: ExpectantDetails)
            = flow<Resource<Boolean>> {

        emit(Resource.loading())

        patientsInitialVisitCollectionReference
            .document(expectantDetails.maternalProfile!!.ancNumber)
            .set(expectantDetails).await()

        emit(Resource.success(true))
    }.catch { exception ->
        emit(Resource.failed(exception.localizedMessage!!))
    }.flowOn(Dispatchers.IO)


    /**Post Medical Surgical History*/
    fun saveInitialVisitMedicalHistory(ancNumber: String,
                                       medicalSurgicalHistory: ExpectantMedicalSurgicalHistory)
            = flow<Resource<Boolean>> {

        emit(Resource.loading())

        patientsInitialVisitCollectionReference
            .document(ancNumber).update(
                "medicalSurgicalHistory", medicalSurgicalHistory)
            .await()

        emit(Resource.success(true))
    }.catch { exception ->
        emit(Resource.failed(exception.localizedMessage!!))
    }.flowOn(Dispatchers.IO)



    /**Post Physical Antenatal Feeding Profile*/
    fun saveInitialVisitPhysicalAntenatal(
        ancNumber: String,
        physicalAntenatalFeeding: ExpectantPhysicalAntenatalFeeding)
            = flow<Resource<Boolean>> {

        emit(Resource.loading())

        patientsInitialVisitCollectionReference
            .document(ancNumber).update(
                "physicalAntenatalFeeding", physicalAntenatalFeeding)
            .await()

        emit(Resource.success(true))
    }.catch { exception ->
        emit(Resource.failed(exception.localizedMessage!!))
    }.flowOn(Dispatchers.IO)
    //endregion

    /**Saving Subsequent Visit*/
    fun saveSubsequentVisit(subsequentVisit: ExpectantSubsequentVisit)
            = flow<Resource<Boolean>> {

        emit(Resource.loading())

        patientsSubsequentVisitCollectionReference.document()
            .set(subsequentVisit).await()

        emit(Resource.success(true))
    }.catch { exception ->
        emit(Resource.failed(exception.localizedMessage!!))
    }.flowOn(Dispatchers.IO)


    /**Saving Delivery Visit*/
    fun saveDeliveryDetails(deliveryDetails: DeliveryDetails)
            = flow<Resource<Boolean>> {

        emit(Resource.loading())

        patientsDeliveryCollectionReference.document()
            .set(deliveryDetails).await()

        emit(Resource.success(true))
    }.catch { exception ->
        emit(Resource.failed(exception.localizedMessage!!))
    }.flowOn(Dispatchers.IO)


}