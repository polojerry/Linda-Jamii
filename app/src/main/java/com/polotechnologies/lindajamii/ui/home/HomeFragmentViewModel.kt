package com.polotechnologies.lindajamii.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.HomeOption
import com.polotechnologies.lindajamii.util.LindaJamiiFirebaseMessagingService

class HomeFragmentViewModel : ViewModel() {
    val TAG = "HomeFragmentViewModel"

    //selected Shop Category
    private val _selectedHomeOption = MutableLiveData<HomeOption>()
    val selectedHomeOption: LiveData<HomeOption>
        get() = _selectedHomeOption

    var fcmToken = ""

    fun displaySelectedProductCategory(homeOption: HomeOption) {
        _selectedHomeOption.value = homeOption
    }

    fun displaySelectedProductCategoryComplete() {
        _selectedHomeOption.value = null
    }

    fun sendTokenToFirestore(newToken: String) {
        Log.d(TAG, "onNewToken: Sending Token to Firebase:::: $newToken")

        val token = HashMap<String, String>()
        token["token"] = newToken

        FirebaseFirestore.getInstance()
            .collection("users")
            .document("testUser")
            .set(token)
    }

}