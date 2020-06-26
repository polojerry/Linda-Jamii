package com.polotechnologies.lindajamii.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.HomeOption

class HomeFragmentViewModel : ViewModel() {

    private val _homeOptions = listOf(
        HomeOption(R.drawable.linda_jamii_logo, "Initial Visit"),
        HomeOption(R.drawable.linda_jamii_logo, "Subsequent Visits"),
        HomeOption(R.drawable.linda_jamii_logo, "Delivery"),
        HomeOption(R.drawable.linda_jamii_logo, "Post Natal Visits"),
        HomeOption(R.drawable.linda_jamii_logo, "Patients")
    )

    //selected Shop Category
    private val _selectedHomeOption = MutableLiveData<HomeOption?>()
    val selectedHomeOption: LiveData<HomeOption?>
        get() = _selectedHomeOption

    var fcmToken = ""

    fun homeOptions(): List<HomeOption> {
        return _homeOptions
    }

    fun displaySelectedProductCategory(homeOption: HomeOption) {
        _selectedHomeOption.value = homeOption
    }

    fun displaySelectedProductCategoryComplete() {
        _selectedHomeOption.value = null
    }


    fun sendTokenToFirestore(newToken: String) {
        val token = HashMap<String, String>()
        token["token"] = newToken

        FirebaseFirestore.getInstance()
            .collection("users")
            .document("testUser")
            .set(token)
    }

}