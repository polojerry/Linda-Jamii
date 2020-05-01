package com.polotechnologies.lindajamii.ui.subsequentVisits

import android.app.Application
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polotechnologies.lindajamii.dataModels.PatientDetails
import com.polotechnologies.lindajamii.dataModels.PatientDetails.*
import com.polotechnologies.lindajamii.database.PatientProfileDAO
import kotlinx.coroutines.*

class SubsequentVisitViewModel(
    val app: Application,
    val database: PatientProfileDAO
) : AndroidViewModel(app) {

    private val _maternalProfile = MutableLiveData<MaternalProfile>()
    val maternalProfile: LiveData<MaternalProfile>
        get() = _maternalProfile

    private val _medicalSurgicalHistory = MutableLiveData<MedicalSurgicalHistory>()
    val medicalSurgicalHistory: LiveData<MedicalSurgicalHistory>
        get() = _medicalSurgicalHistory

    private val _physicalExamination = MutableLiveData<PhysicalExamination>()
    val physicalExamination: LiveData<PhysicalExamination>
        get() = _physicalExamination

    private val _antenatalProfile = MutableLiveData<AntenatalProfile>()
    val antenatalProfile: LiveData<AntenatalProfile>
        get() = _antenatalProfile



    //Inserted Profile ID
    private val _insertedDetailId = MutableLiveData<Long>()
    val insertedDetailId: LiveData<Long>
        get() = _insertedDetailId

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun saveMaternalProfile(maternalProfile: MaternalProfile) {
        _maternalProfile.value = maternalProfile
    }
    fun saveMedicalSurgicalHistory(medicalSurgicalHistory: MedicalSurgicalHistory) {
        _medicalSurgicalHistory.value = medicalSurgicalHistory
    }
    fun savePhysicalExamination(physicalExamination: PhysicalExamination) {
        _physicalExamination.value = physicalExamination
    }
    fun saveAntenatalProfile(antenatalProfile: AntenatalProfile) {
        _antenatalProfile.value = antenatalProfile
    }

    fun savePatientProfile(){
        coroutineScope.launch {
            insertPatientToDatabase()
        }
    }

    private suspend fun insertPatientToDatabase() {

        val patientProfile = PatientDetails(
            0L,
            _maternalProfile.value,
            _medicalSurgicalHistory.value,
            _physicalExamination.value,
            _antenatalProfile.value
        )
        withContext(Dispatchers.IO){
            _insertedDetailId.value = database.insert(patientProfile)
        }
    }


    override fun onCleared() {
        viewModelJob.cancel()
    }

}