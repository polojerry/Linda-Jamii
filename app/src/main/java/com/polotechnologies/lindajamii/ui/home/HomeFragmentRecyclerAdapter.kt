package com.polotechnologies.lindajamii.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polotechnologies.lindajamii.dataModels.HomeOption
import com.polotechnologies.lindajamii.databinding.ItemHomeCardBinding

class HomeFragmentRecyclerAdapter(val homeOptions : List<HomeOption>,private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<HomeFragmentRecyclerAdapter.OptionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder.from(parent)
    }

    override fun getItemCount() = homeOptions.size

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val option = homeOptions[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(option)
        }
        holder.bind(option)
    }

    class OnClickListener(val clickListener : (homeOption: HomeOption) -> Unit){
        fun onClick(homeOption: HomeOption) = clickListener(homeOption)
    }

    class OptionViewHolder(private val binding: ItemHomeCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(homeOption: HomeOption) {
            binding.option = homeOption
            binding.executePendingBindings()

        }

        companion object{
            fun from (parent: ViewGroup) : OptionViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemHomeCardBinding.inflate(inflater, parent, false)
                return OptionViewHolder(binding)
            }
        }
    }
}