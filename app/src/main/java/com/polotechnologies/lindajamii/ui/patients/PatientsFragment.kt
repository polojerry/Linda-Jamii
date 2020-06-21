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
import androidx.navigation.fragment.findNavController
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding
import com.polotechnologies.lindajamii.network.FirestoreService
import com.polotechnologies.lindajamii.network.Resource
import com.polotechnologies.lindajamii.util.ExpectantVisitNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatientsFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var mBinding: FragmentPatientsBinding
    private lateinit var mViewModel: PatientsViewModel
    private lateinit var mAdapter : PatientsRecyclerAdapter

    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_patients, container, false)
        mBinding.lifecycleOwner = this

        val factory = PatientsViewModelFactory(requireActivity().application)
        mViewModel = ViewModelProvider(this, factory)[PatientsViewModel::class.java]

        inflateSearchMenu()
        setDisplayDetails()
        setObservers()
        return mBinding.root
    }

    private fun setDisplayDetails() {
        mAdapter = PatientsRecyclerAdapter(PatientsRecyclerAdapter.OnClickListener { selectedPatient ->
                mViewModel.displaySelectedPatient(selectedPatient)
            })

        mBinding.recyclerPatients.adapter = mAdapter

        uiScope.launch {
            fetchPatients()
        }

    }

    private suspend fun fetchPatients() {

        mViewModel.fetchPatients().collect { resource ->
            when (resource) {

                is Resource.Loading -> {
                    mBinding.swipeRefreshPatients.isRefreshing = true
                }

                is Resource.Success -> {
                    mAdapter.submitList(resource.data)
                    mBinding.swipeRefreshPatients.isRefreshing = false
                }
                is Resource.Failed -> {
                    Toast.makeText(requireContext(), "Failed: ${resource.message}", Toast.LENGTH_SHORT).show()
                    mBinding.swipeRefreshPatients.isRefreshing = false
                }
            }


        }
    }

    private fun setObservers() {
        mViewModel.selectedPatient.observe(viewLifecycleOwner, Observer { expectantDetails ->
            if (expectantDetails != null) {
                val action =
                    PatientsFragmentDirections.actionPatientsFragmentToPatientsDetailsFragment(
                        expectantDetails.patientId
                    )
                findNavController().navigate(action)
                mViewModel.displaySelectedPatientDone()
            }
        })
    }

    private fun inflateSearchMenu() {
        val toolbar = mBinding.toolbarSearchPatient
        val searchManager =
            requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = toolbar.menu.findItem(R.id.action_search_patient).actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setOnQueryTextListener(this@PatientsFragment)
            setIconifiedByDefault(true)
            isSubmitButtonEnabled = false
            isIconified = false
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }


}