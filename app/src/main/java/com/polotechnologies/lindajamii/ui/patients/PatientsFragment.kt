package com.polotechnologies.lindajamii.ui.patients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.HomeOption
import com.polotechnologies.lindajamii.dataModels.Patients
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding
import com.polotechnologies.lindajamii.ui.home.HomeFragment
import com.polotechnologies.lindajamii.ui.home.HomeFragmentRecyclerAdapter
import com.polotechnologies.lindajamii.ui.home.HomeFragmentViewModel
import com.polotechnologies.lindajamii.ui.patients.PatientsFragment.Companion.PATIENTS

internal class PatientsFragment : Fragment(R.layout.fragment_patients) {

    private lateinit var mBinding: FragmentPatientsBinding
    private val mViewModel by lazy {
        ViewModelProvider(this).get(PatientFragmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentPatientsBinding.bind(view)
        displayPatients()
        setObserver()
    }


    private fun setObserver() {
        mViewModel.selectedPatient.observe(viewLifecycleOwner, Observer { patient ->
            if (patient != null) {
                val action =
                    PatientsFragmentDirections.actionPatientsFragmentToPatientsDetailsFragment(
                        patient
                    )
                findNavController().navigate(action)
                mViewModel.displaySelectedPatientComplete()
            }

        })
    }

    private fun displayPatients() {
        mBinding.recyclerPatients.adapter =
            PatientsFragmentRecyclerAdapter(
                PATIENTS,
                PatientsFragmentRecyclerAdapter.OnClickListener { patients ->
                    mViewModel.displaySelectedPatient(patients)
                })
    }

    companion object {
        val PATIENTS = listOf<Patients>(
            Patients("Winnie Awino", "254790689212", "01/04/2020", "12/3/2020", "Widowed"),
            Patients("Mercy Korir", "2723071546", "01/05/2020", "10/3/2020", "Maried"),
            Patients("Jennifer Atieno", "254741554689", "10/04/2020", "15/4/2020", "Single"),
            Patients("Sheila Wangare", "254790689212", "12/05/2020", "04/6/2020", "Maried"),
            Patients("Josphene Kemuntu", "254724790233", "13/05/2020", "10/3/2020", "Single")
        )
    }
}