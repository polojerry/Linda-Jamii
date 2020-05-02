package com.polotechnologies.lindajamii.ui.delivery

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
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentDeliveryBinding
import com.polotechnologies.lindajamii.ui.subsequentVisits.SubsequentVisitViewModel
import com.polotechnologies.lindajamii.ui.subsequentVisits.SubsequentVisitViewModelFactory

/**
 */
class DeliveryFragment : Fragment() {

    private lateinit var mBinding : FragmentDeliveryBinding
    private lateinit var mViewModel: DeliveryViewModel
    private lateinit var mDatabase: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery, container, false)
        mDatabase = FirebaseFirestore.getInstance()

        val factory = DeliveryViewModelFactory(mDatabase, mBinding)
        mViewModel = ViewModelProvider(this, factory)[DeliveryViewModel::class.java]


        setObserver()
        mBinding.buttonDeliveryFinish.setOnClickListener {
            if(mViewModel.isFieldsValid()){
                mBinding.progressBarDelivery.visibility = View.VISIBLE
                mBinding.buttonDeliveryFinish.isEnabled = false
                mViewModel.saveDeliveryDetails()

            }
        }

        setFilledDropDownMenu()
        return mBinding.root
    }

    private fun setObserver() {
        mViewModel.writeStatus.observe(viewLifecycleOwner, Observer { status ->
            mBinding.progressBarDelivery.visibility = View.INVISIBLE
            if (status == true) {
                Toast.makeText(context!!.applicationContext, "Delivery Details Done", Toast.LENGTH_SHORT).show()
                activity!!.onBackPressed()
            } else {
                Toast.makeText(
                    context!!.applicationContext,
                    "Failed: ${mViewModel.exception.value!!.localizedMessage}",
                    Toast.LENGTH_SHORT
                )
            }

        })
    }

    private fun setFilledDropDownMenu() {
        val yes_no_drop_down = resources.getStringArray(R.array.filled_drop_down_yes_no)
        val hiv_couselling_category = resources.getStringArray(R.array.filled_drop_down_hiv)

        val categoryYesNoAdapter  = ArrayAdapter<String>(
            context!!.applicationContext,
            R.layout.layout_dropdown_menu_popup,
            yes_no_drop_down
        )
        val categoryHivAdapter  = ArrayAdapter<String>(
            context!!.applicationContext,
            R.layout.layout_dropdown_menu_popup,
            hiv_couselling_category
        )

        mBinding.textDeliveryHivTested.setAdapter(categoryYesNoAdapter)
        mBinding.textDeliveryCounselAndTest.setAdapter(categoryHivAdapter)
        mBinding.textDeliveryObstructedLabour.setAdapter(categoryYesNoAdapter)
        mBinding.textDeliveryRescusitationDone.setAdapter(categoryYesNoAdapter)
    }

}