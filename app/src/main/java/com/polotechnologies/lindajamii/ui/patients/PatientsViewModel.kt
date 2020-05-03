package com.polotechnologies.lindajamii.ui.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PatientsViewModel(mMflNumber: String, val mDatabase: FirebaseFirestore) : ViewModel() {

    //Response from Firestore
    private val _patientsStatus = MutableLiveData<HeroApiStatus>()
    val patientsStatus: LiveData<HeroApiStatus>
        get() = _patientsStatus

    //List from Firestore
    private val _patientsData = MutableLiveData<List<ExpectantDetails>>()
    val patientsData: LiveData<List<ExpectantDetails>>
        get() = _patientsData

    //Selected Patient
    private val _selectedPatient = MutableLiveData<ExpectantDetails>()
    val selectedPatient : LiveData<ExpectantDetails>
        get () = _selectedPatient

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        fetchPatients(mMflNumber)
    }


    fun fetchPatients(mMflNumber: String?) {

        coroutineScope.launch {


        }

    }

    fun displaySelectedPatient(expectantDetails: ExpectantDetails){
        _selectedPatient.value = expectantDetails
    }

    fun displaySelectedHeroComplete(){
        _selectedPatient.value = null
    }


    override fun onCleared() {
        viewModelJob.cancel()
    }
}

public enum class HeroApiStatus{
    LOADING,
    NO_INTERNET_CONNECTION,
    DONE,
    NONE,
    NO_MATCH
}