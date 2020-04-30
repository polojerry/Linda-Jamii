package com.polotechnologies.lindajamii.ui.patients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polotechnologies.lindajamii.dataModels.Patients
import com.polotechnologies.lindajamii.databinding.ItemPatientBinding

class PatientsFragmentRecyclerAdapter(val patients : List<Patients>, private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<PatientsFragmentRecyclerAdapter.PatientsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        return PatientsViewHolder.from(parent)
    }

    override fun getItemCount() = patients.size

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val patient = patients[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(patient)
        }
        holder.bind(patient)
    }

    class OnClickListener(val clickListener : (patient: Patients) -> Unit){
        fun onClick(patient: Patients) = clickListener(patient)
    }

    class PatientsViewHolder(private val binding: ItemPatientBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(patient: Patients) {
            binding.patient = patient
            binding.executePendingBindings()

        }

        companion object{
            fun from (parent: ViewGroup) : PatientsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemPatientBinding.inflate(inflater, parent, false)
                return PatientsViewHolder(binding)
            }
        }
    }
}