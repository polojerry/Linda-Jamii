package com.polotechnologies.lindajamii.ui.patients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.databinding.ItemPatientBinding
import com.polotechnologies.lindajamii.ui.patients.PatientsRecyclerAdapter.*

class PatientsRecyclerAdapter(private val onClickListener: OnClickListener)  :
    ListAdapter<ExpectantDetails, PatientsViewHolder>(PatientsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        return PatientsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(patient)
        }

    }

    class OnClickListener(val clickListener : (patient: ExpectantDetails) -> Unit){
        fun onClick(patient: ExpectantDetails) = clickListener(patient)
    }

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

    class PatientsDiffCallBack : DiffUtil.ItemCallback<ExpectantDetails>(){
        override fun areItemsTheSame(oldItem: ExpectantDetails, newItem: ExpectantDetails): Boolean {
            return oldItem.patientId === newItem.patientId
        }

        override fun areContentsTheSame(oldItem: ExpectantDetails, newItem: ExpectantDetails): Boolean {
            return oldItem == newItem
        }

    }
}