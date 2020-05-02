package com.polotechnologies.lindajamii.ui.patients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.Patients
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding

class PatientsFragment : Fragment() {

    lateinit var mBinding : FragmentPatientsBinding
    lateinit var mViewModel: PatientsFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_patients, container, false)

        mViewModel = ViewModelProvider(this)[PatientsFragmentViewModel::class.java]

        displayPatients()
        setObserver()


        return mBinding.root
    }

    private fun setObserver() {
        mViewModel.selectedPatient.observe(viewLifecycleOwner, Observer {patient->
            if(patient!=null){
                val action  = PatientsFragmentDirections.actionPatientsFragmentToPatientsDetailsFragment(patient)
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

    companion object{
        val PATIENTS = listOf<Patients>(
            Patients("Winnie Awino","254790689212","01/04/2020","12/3/2020","Widowed"),
            Patients("Mercy Korir","2723071546","01/05/2020","10/3/2020","Maried"),
            Patients("Jennifer Atieno","254741554689","10/04/2020","15/4/2020","Single"),
            Patients("Sheila Wangare","254790689212","12/05/2020","04/6/2020","Maried"),
            Patients("Josphene Kemuntu","254724790233","13/05/2020","10/3/2020","Single")


        )
    }

}