package com.polotechnologies.lindajamii.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.HomeOption

class HomeFragmentViewModel : ViewModel() {

    //selected Shop Category
    private val _selectedHomeOption = MutableLiveData<HomeOption>()
    val selectedHomeOption: LiveData<HomeOption>
        get() = _selectedHomeOption


    fun displaySelectedProductCategory(homeOption: HomeOption) {
        _selectedHomeOption.value = homeOption
    }

    fun displaySelectedProductCategoryComplete() {
        _selectedHomeOption.value = null
    }

}