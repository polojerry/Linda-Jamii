package com.polotechnologies.lindajamii.ui.patients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.databinding.ItemPatientBinding

class PatientsViewHolder private constructor(val binding : ItemPatientBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(patient: ExpectantDetails){
        binding.patient = patient
        binding.executePendingBindings()
    }

    companion object{
        fun from (parent: ViewGroup): PatientsViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPatientBinding.inflate(layoutInflater, parent, false)

            return PatientsViewHolder(binding)
        }
    }

}
