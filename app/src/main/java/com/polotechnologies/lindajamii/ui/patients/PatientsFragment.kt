package com.polotechnologies.lindajamii.ui.patients

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.firestore.paging.LoadingState
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding

class PatientsFragment : Fragment(), SearchView.OnQueryTextListener {

    lateinit var mBinding : FragmentPatientsBinding
    lateinit var mViewModel: PatientsViewModel
    lateinit var mDatabase: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_patients, container, false)
        mBinding.lifecycleOwner = this
        mDatabase = FirebaseFirestore.getInstance()

        val factory = PatientsViewModelFactory(mBinding,"", mDatabase)
        mViewModel = ViewModelProvider(this,factory)[PatientsViewModel::class.java]

        inflateSearchMenu()
        setObservers()

        mViewModel.fetchPatients()
        return mBinding.root
    }

    private fun inflateSearchMenu() {
        val toolbar = mBinding.toolbarSearchPatient
        val searchManager = context!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = toolbar.menu.findItem(R.id.action_search_patient).actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            setOnQueryTextListener(this@PatientsFragment)
            setIconifiedByDefault(true)
            isSubmitButtonEnabled = false
            isIconified = false
        }

    }

    private fun setObservers(){
        mViewModel.selectedPatient.observe(viewLifecycleOwner, Observer {expectantDetails->
            if(expectantDetails!=null){
                TODO()
            }
        })

        mViewModel.patientsStatus.observe(viewLifecycleOwner, Observer {status->
            when (status) {
                LoadingState.LOADING_INITIAL -> {
                    mBinding.swipeRefreshPatients.isRefreshing = true
                }

                LoadingState.LOADING_MORE -> {
                    mBinding.swipeRefreshPatients.isRefreshing = true
                }

                LoadingState.LOADED -> {
                    mBinding.swipeRefreshPatients.isRefreshing = false
                }

                LoadingState.ERROR -> {
                    mBinding.swipeRefreshPatients.isRefreshing = false
                    Toast.makeText(context, "Error Loading Patients: Swipe to refresh", Toast.LENGTH_SHORT).show()
                }

                LoadingState.FINISHED -> {
                    mBinding.swipeRefreshPatients.isRefreshing = false
                }

            }

        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}