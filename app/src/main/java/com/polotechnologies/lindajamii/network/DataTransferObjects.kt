package com.polotechnologies.lindajamii.network

import com.polotechnologies.lindajamii.dataModels.ExpectantDetails

/**
 * DataTransferObjects is responsible for parsing responses from the server
 * or formatting objects to send to the server.
 */

/**
 * PatientHolder holds a list of Patients.
 *
 */

data class NetworkPatientsContainer(val expectantPatients: List<ExpectantDetails>)


/**
 * Convert Network results to database objects
 */
fun NetworkPatientsContainer.asDatabaseModel(): Array<ExpectantDetails> {
    return expectantPatients.map {expectantPatient->
        ExpectantDetails(
            patientId = expectantPatient.patientId,
            timeStamp = expectantPatient.timeStamp,
            maternalProfile = expectantPatient.maternalProfile,
            medicalSurgicalHistory = expectantPatient.medicalSurgicalHistory,
            physicalAntenatalFeeding = expectantPatient.physicalAntenatalFeeding)
    }.toTypedArray()
}