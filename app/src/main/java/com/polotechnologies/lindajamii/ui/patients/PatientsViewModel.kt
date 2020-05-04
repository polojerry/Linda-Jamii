package com.polotechnologies.lindajamii.ui.patients

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class PatientsViewModel(val mBinding: FragmentPatientsBinding, mMflNumber: String, val mDatabase: FirebaseFirestore) : ViewModel() {

    //Response from Firestore
    private val _patientsStatus = MutableLiveData<LoadingState>()
    val patientsStatus: LiveData<LoadingState>
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
        fetchPatients()
    }


    fun fetchPatients() {
        //firestore query
        val mQuery = mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("initialVisit")
            .limit(15)

        //paging configuration
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(2)
            .setPageSize(5)
            .build()

        // adapter configuration
        val options = FirestorePagingOptions.Builder<ExpectantDetails>()
            .setLifecycleOwner(mBinding.lifecycleOwner!!)
            .setQuery(mQuery, config, ExpectantDetails::class.java)
            .build()

        val mAdapter  = object :
            FirestorePagingAdapter<ExpectantDetails, PatientsViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
                return PatientsViewHolder.from(parent)
            }

            override fun onBindViewHolder(holder: PatientsViewHolder,
                                          postion: Int, expectantDetails: ExpectantDetails) {
                holder.bind(expectantDetails)
            }

            override fun onLoadingStateChanged(state: LoadingState) {
                when (state) {
                    LoadingState.LOADING_INITIAL -> {
                        _patientsStatus.value = LoadingState.LOADING_INITIAL
                    }

                    LoadingState.LOADING_MORE -> {
                        _patientsStatus.value = LoadingState.LOADING_MORE
                    }

                    LoadingState.LOADED -> {
                        _patientsStatus.value = LoadingState.LOADED
                    }

                    LoadingState.ERROR -> {
                        _patientsStatus.value = LoadingState.ERROR
                    }

                    LoadingState.FINISHED -> {
                        _patientsStatus.value = LoadingState.FINISHED
                    }
                }
            }

        }

        mBinding.recyclerPatients.adapter = mAdapter
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}
