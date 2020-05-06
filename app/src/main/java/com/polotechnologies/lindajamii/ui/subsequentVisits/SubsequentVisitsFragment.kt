package com.polotechnologies.lindajamii.ui.subsequentVisits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel
import com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory.MedicalSurgicalHistoryFragmentDirections


/**
 *
 */
class SubsequentVisitsFragment : Fragment() {

    private lateinit var mBinding: FragmentSubsequentVisitsBinding
    private lateinit var mViewModel: SubsequentVisitViewModel
    private lateinit var mDatabase: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_subsequent_visits, container, false)
        mDatabase = FirebaseFirestore.getInstance()

        val firestoreServiceViewModel = ViewModelProvider(this)[FirestoreServiceViewModel::class.java]
        val factory = SubsequentVisitViewModelFactory(mBinding,firestoreServiceViewModel)
        mViewModel = ViewModelProvider(this, factory)[SubsequentVisitViewModel::class.java]

        setObserver()
        mBinding.buttonFinishSubsequentVisit.setOnClickListener {
            if(mViewModel.isFieldsValid()){
                mBinding.progressBarSubsequentVisit.visibility = View.VISIBLE
                mBinding.buttonFinishSubsequentVisit.isEnabled = false
                mViewModel.saveMedicalSurgicalHistory()

            }
        }

        return mBinding.root
    }

    private fun setObserver() {
        mViewModel.exception.observe(viewLifecycleOwner, Observer { exception ->
            mBinding.progressBarSubsequentVisit.visibility = View.INVISIBLE
            if (exception == null) {
                Toast.makeText(context!!.applicationContext, "Subsequent Visit Done", Toast.LENGTH_SHORT).show()
                activity!!.onBackPressed()
            } else {
                Toast.makeText(
                    context!!.applicationContext,
                    "Failed: ${exception.localizedMessage}",
                    Toast.LENGTH_SHORT
                )
            }

        })
    }

}