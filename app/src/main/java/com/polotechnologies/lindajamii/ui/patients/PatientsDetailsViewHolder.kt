package com.polotechnologies.lindajamii.ui.patients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.databinding.ItemPatientBinding

class PatientsDetailsViewHolder private constructor(val binding : ItemPatientBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(patient: ExpectantDetails){
        binding.patient = patient
        binding.executePendingBindings()
    }

    companion object{
        fun from (parent: ViewGroup): PatientsDetailsViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPatientBinding.inflate(layoutInflater, parent, false)

            return PatientsDetailsViewHolder(binding)
        }
    }

}

/*
class PatientsDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val patientName: AppCompatTextView = itemView.findViewById(R.id.text_patient_name)
    private val patientAncNumber: AppCompatTextView = itemView.findViewById(R.id.text_patient_anc_number)
    private val patientEdd: AppCompatTextView = itemView.findViewById(R.id.text_patient_anc_ddd)
    private val patientAge: AppCompatTextView = itemView.findViewById(R.id.text_patient_anc_age)

    fun bind(patient: ExpectantDetails){
        patientName.text = patient.maternalProfile?.nameOfClient
        patientAncNumber.text = patient.maternalProfile?.ancNumber
        patientEdd.text = patient.maternalProfile?.edd
        patientAge.text = patient.maternalProfile?.ageOfClient
    }


}
*/
