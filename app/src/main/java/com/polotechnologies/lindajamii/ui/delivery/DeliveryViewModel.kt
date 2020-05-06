package com.polotechnologies.lindajamii.ui.delivery


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.DeliveryDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantSubsequentVisit
import com.polotechnologies.lindajamii.dataModels.SubsequentVisit
import com.polotechnologies.lindajamii.databinding.FragmentDeliveryBinding
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel


class DeliveryViewModel(
    private val firestoreServiceViewModel: FirestoreServiceViewModel,
    val mBinding: FragmentDeliveryBinding
) :
    ViewModel() {
    private val _writeException = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _writeException

    private var registrationNumber = ""
    private var duringOfPregnancy = ""
    private var hivTested = ""
    private var counselAndTest = ""
    private var modeOfDelivery = ""
    private var dateOfDelivery = ""
    private var bloodLoss = ""
    private var preEclapsia = ""
    private var eclampsia = ""
    private var apgarScore = ""
    private var rescusitationDone = ""
    private var babayVitK = ""
    private var babyTeo = ""
    private var birthWeight = ""
    private var birthLength = ""
    private var headCircumference = ""
    private var placeOfDelivery = ""
    private var conductedBy = ""


    fun isFieldsValid(): Boolean {
        var isValid = false

        registrationNumber = mBinding.textDeliveryRegistrationNumber.text.toString()
        duringOfPregnancy = mBinding.textDeliveryPregnancyDuration.text.toString()
        hivTested = mBinding.textDeliveryHivTested.text.toString()
        counselAndTest = mBinding.textDeliveryCounselAndTest.text.toString()
        modeOfDelivery = mBinding.textDeliveryModeOfDelivery.text.toString()
        dateOfDelivery = mBinding.textDeliveryDateOfDelivery.text.toString()
        bloodLoss = mBinding.textDeliveryBloodLoss.text.toString()
        preEclapsia = mBinding.textDeliveryPreEclampia.text.toString()
        eclampsia = mBinding.textDeliveryEclampia.text.toString()
        apgarScore = mBinding.textDeliveryApgarScore.text.toString()
        rescusitationDone = mBinding.textDeliveryRescusitationDone.text.toString()
        babayVitK = mBinding.textDeliveryBabyVitK.text.toString()
        babyTeo = mBinding.textDeliveryBabyTeo.text.toString()
        birthWeight = mBinding.textDeliveryBabyBirthWeight.text.toString()
        birthLength = mBinding.textDeliveryBabyBirthLength.text.toString()
        headCircumference = mBinding.textDeliveryBabyBirthHeadCircumference.text.toString()
        placeOfDelivery = mBinding.textDeliveryBabyBirthPlaceOfBirth.text.toString()
        conductedBy = mBinding.textDeliveryBabyBirthConductedBy.text.toString()


        if (registrationNumber == "") {
            isValid = false
            mBinding.textLayoutDeliveryRegistrationNumber.error = "Required"
        }

        if (duringOfPregnancy == "") {
            isValid = false
            mBinding.textLayoutDeliveryPregnancyDuration.error = "Required"
        }

        if (hivTested == "") {
            isValid = false
            mBinding.textLayoutDeliveryHivTested.error = "Required"
        }
        if (counselAndTest == "") {
            isValid = false
            mBinding.textLayoutDeliveryCounselAndTest.error = "Required"
        }
        if (modeOfDelivery == "") {
            isValid = false
            mBinding.textLayoutDeliveryModeOfDelivery.error = "Required"
        }
        if (dateOfDelivery == "") {
            mBinding.textLayoutDeliveryDateOfDelivery.error = "Required"
            isValid = false
        }
        if (bloodLoss == "") {
            isValid = false
            mBinding.textLayoutDeliveryBloodLoss.error = "Required"
        }
        if (preEclapsia == "") {
            isValid = false
            mBinding.textLayoutDeliveryPreEclampia
                .error = "Required"
        }

        if (eclampsia == "") {
            isValid = false
            mBinding.textLayoutDeliveryPreEclampia.error = "Required"
        }

        if (apgarScore == "") {
            isValid = false
            mBinding.textLayoutDeliveryApgarScore.error = "Required"
        }
        if (rescusitationDone == "") {
            isValid = false
            mBinding.textLayoutDeliveryRescusitationDone.error = "Required"
        }
        if (babayVitK == "") {
            isValid = false
            mBinding.textLayoutDeliveryBabyVitK.error = "Required"
        }
        if (babyTeo == "") {
            isValid = false
            mBinding.textLayoutDeliveryBabyTeo.error = "Required"
        }
        if (birthWeight == "") {
            isValid = false
            mBinding.textLayoutDeliveryBabyBirthWeight.error = "Required"
        }

        if (birthLength == "") {
            isValid = false
            mBinding.textLayoutDeliveryBabyBirthLength.error = "Required"
        }
        if (headCircumference == "") {
            isValid = false
            mBinding.textLayoutDeliveryBabyBirthHeadCircumference.error = "Required"
        }

        if (placeOfDelivery == "") {
            isValid = false
            mBinding.textLayoutDeliveryBabyBirthPlaceOfBirth.error = "Required"
        }
        if (conductedBy == "") {
            isValid = false
            mBinding.textLayoutDeliveryBabyBirthConductedBy.error = "Required"
        }


        if (registrationNumber != "" && duringOfPregnancy != "" && hivTested != "" && counselAndTest != "" && modeOfDelivery != ""
            && dateOfDelivery != "" && bloodLoss != "" && preEclapsia != "" && eclampsia != "" && apgarScore != ""
            && rescusitationDone != "" && babayVitK != "" && babyTeo != "" && birthWeight != "" && birthLength != "" && headCircumference != ""
            && placeOfDelivery != "" && conductedBy != ""
        ) {
            isValid = true
        }

        return isValid

    }

    fun saveDeliveryDetails() {
        val deliveryDetails = DeliveryDetails(
            registrationNumber, duringOfPregnancy, hivTested, counselAndTest,
            modeOfDelivery, dateOfDelivery, bloodLoss, preEclapsia, eclampsia,
            apgarScore, rescusitationDone, babayVitK, babyTeo, birthWeight,
            birthLength, headCircumference, placeOfDelivery, conductedBy
        )


        firestoreServiceViewModel.saveDeliveryDetails(deliveryDetails).also {writeException->
            if(writeException.value == null){
                _writeException.value = null
            }else{
                _writeException.value = writeException.value

            }
        }

    }


}