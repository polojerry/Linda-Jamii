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
import com.polotechnologies.lindajamii.network.FirestoreService
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel

class PatientsFragment : Fragment(), SearchView.OnQueryTextListener {

    lateinit var mBinding : FragmentPatientsBinding
    lateinit var mViewModel: PatientsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_patients, container, false)
        mBinding.lifecycleOwner = this

        val factory = PatientsViewModelFactory(activity!!.application)
        mViewModel = ViewModelProvider(this,factory)[PatientsViewModel::class.java]

        inflateSearchMenu()
        setDisplayDetails()
        setObservers()
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

    private fun setDisplayDetails() {
        val adapter = PatientsRecyclerAdapter(PatientsRecyclerAdapter.OnClickListener{ reportedIncident->
            Toast.makeText(context?.applicationContext, "${reportedIncident.maternalProfile?.nameOfClient}", Toast.LENGTH_SHORT).show()
        })

        mBinding.recyclerPatients.adapter = adapter
        mViewModel.patientsListData.observe(viewLifecycleOwner, Observer {patientsList->
            mViewModel.fetchPatients()
            adapter.submitList(patientsList)

        })
    }

    private fun setObservers(){
        mViewModel.repoIsLoading.observe(viewLifecycleOwner, Observer {isLoading->
                mBinding.swipeRefreshPatients.isRefreshing = isLoading
        })

        mViewModel.selectedPatient.observe(viewLifecycleOwner, Observer {expectantDetails->
            if(expectantDetails!=null){
                TODO()
            }
        })

        mBinding.swipeRefreshPatients.setOnRefreshListener {
            mViewModel.fetchPatients()
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}