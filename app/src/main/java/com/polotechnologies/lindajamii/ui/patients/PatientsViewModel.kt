package com.polotechnologies.lindajamii.ui.patients

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.firebase.ui.firestore.paging.LoadingState
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase.Companion.getDatabase
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel
import com.polotechnologies.lindajamii.repository.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/*class PatientsViewModel(val mBinding: FragmentPatientsBinding,
                        private val firestoreServiceViewModel: FirestoreServiceViewModel) : ViewModel() {
    // List from Firestore
    private val _patientsList = MutableLiveData<List<ExpectantDetails>>()
    val patientsListData: LiveData<List<ExpectantDetails>>
        get() = _patientsList

    //Selected Patient
    private val _selectedPatient = MutableLiveData<ExpectantDetails>()
    val selectedPatient : LiveData<ExpectantDetails>
        get () = _selectedPatient

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        fetchPatients()
    }

    private fun fetchPatients()  = viewModelScope.launch{
        *//*firestoreServiceViewModel.getPatients().observe(mBinding.lifecycleOwner!!, Observer {list->
            _patientsList.value= list
        })*//*
    }



    override fun onCleared() {
        viewModelJob.cancel()
    }
}*/
class PatientsViewModel(application: Application,
                        firestoreServiceViewModel: FirestoreServiceViewModel) : ViewModel() {

/*
    // List from Firestore
    private val _patientsList = MutableLiveData<List<ExpectantDetails>>()
    val patientsListData: LiveData<List<ExpectantDetails>>
        get() = _patientsList
*/

    //Selected Patient
    private val _selectedPatient = MutableLiveData<ExpectantDetails>()
    val selectedPatient : LiveData<ExpectantDetails>
        get () = _selectedPatient

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    val patientRepository = PatientRepository(firestoreServiceViewModel,database)
    init{
        fetchPatients()
    }

    private fun fetchPatients()  = viewModelScope.launch{
        patientRepository.refreshPatients()
    }

    val patientsListData = patientRepository.patients


    override fun onCleared() {
        viewModelJob.cancel()
    }
}



