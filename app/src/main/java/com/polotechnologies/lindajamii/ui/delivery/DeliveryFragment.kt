package com.polotechnologies.lindajamii.ui.delivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentDeliveryBinding
import com.polotechnologies.lindajamii.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 */
class DeliveryFragment : Fragment() {

    private lateinit var mBinding: FragmentDeliveryBinding
    private lateinit var mViewModel: DeliveryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery, container, false)
        mBinding.buttonDeliveryFinish.setOnClickListener { postDeliveryDetails() }


        val factory = DeliveryViewModelFactory(requireActivity().application, mBinding)
        mViewModel = ViewModelProvider(this, factory)[DeliveryViewModel::class.java]


        setFilledDropDownMenu()
        return mBinding.root
    }

    private fun postDeliveryDetails() {
        if (!mViewModel.isFieldsValid()) return

        mBinding.buttonDeliveryFinish.isEnabled = false

        mViewModel.writeStatusLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            when (isLoading) {
                true -> progressBarVisibility(View.VISIBLE)
                else -> progressBarVisibility(View.GONE)
            }

        })

        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.saveDeliveryDetails().collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                        mViewModel.setIsLoading(true)
                    }


                    is Resource.Success -> {
                        mViewModel.setIsLoading(false)
                        requireActivity().onBackPressed()
                    }

                    is Resource.Failed -> {
                        mViewModel.setIsLoading(false)
                        toastMessage("Failed: ${resource.message}")
                    }
                }
            }
        }
    }


    private fun setFilledDropDownMenu() {
        val yesNoDropDown = resources.getStringArray(R.array.filled_drop_down_yes_no)
        val hivCounsellingCategories = resources.getStringArray(R.array.filled_drop_down_hiv)

        val categoryYesNoAdapter = ArrayAdapter<String>(
            requireContext().applicationContext,
            R.layout.layout_dropdown_menu_popup,
            yesNoDropDown
        )
        val categoryHivAdapter = ArrayAdapter<String>(
            requireContext().applicationContext,
            R.layout.layout_dropdown_menu_popup,
            hivCounsellingCategories
        )

        mBinding.textDeliveryHivTested.setAdapter(categoryYesNoAdapter)
        mBinding.textDeliveryBloodLoss.setAdapter(categoryYesNoAdapter)
        mBinding.textDeliveryObstructedLabour.setAdapter(categoryYesNoAdapter)
        mBinding.textDeliveryRescusitationDone.setAdapter(categoryYesNoAdapter)
        mBinding.textDeliveryCounselAndTest.setAdapter(categoryHivAdapter)

    }


    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun progressBarVisibility(visibility: Int) {
        mBinding.progressBarDelivery.visibility = visibility
    }

}