package com.polotechnologies.lindajamii.ui.subsequentVisits


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantSubsequentVisit
import com.polotechnologies.lindajamii.dataModels.SubsequentVisit
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding


class SubsequentVisitViewModel(
    val mDatabase: FirebaseFirestore,
    val mBinding: FragmentSubsequentVisitsBinding
) :
    ViewModel() {

    private val _writeStatus = MutableLiveData<Boolean>()
    val writeStatus: LiveData<Boolean>
        get() = _writeStatus

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    private var registrationNumber = ""
    private var numberOfVisit = ""
    private var dateOfVisit = ""
    private var urine = ""
    private var weight = ""
    private var bp = ""
    private var hb = ""
    private var pallor = ""
    private var maturity = ""
    private var fundalHeight = ""
    private var presentation = ""
    private var lie = ""
    private var foetalHeart = ""
    private var foetalMovement = ""
    private var nextVisit = ""


    fun isFieldsValid(): Boolean {
        var isValid = false

        registrationNumber = mBinding.textSubsequentVisitsRegistrationNumber.text.toString()
        numberOfVisit = mBinding.textSubsequentVisitsNumberOfVisit.text.toString()
        dateOfVisit = mBinding.textSubsequentVisitsDate.text.toString()
        urine = mBinding.textSubsequentVisitsUrine.text.toString()
        weight = mBinding.textSubsequentVisitsWeight.text.toString()
        bp = mBinding.textSubsequentVisitsBp.text.toString()
        hb = mBinding.textSubsequentVisitsHb.text.toString()
        pallor = mBinding.textSubsequentVisitsPallor.text.toString()
        maturity = mBinding.textSubsequentVisitsMaturity.text.toString()
        fundalHeight = mBinding.textSubsequentVisitsFundalHeight.text.toString()
        presentation = mBinding.textSubsequentVisitsPresentation.text.toString()
        lie = mBinding.textSubsequentVisitsLie.text.toString()
        foetalHeart = mBinding.textSubsequentVisitsFoetalHeart.text.toString()
        foetalMovement = mBinding.textSubsequentVisitsFoetalMvt.text.toString()
        nextVisit = mBinding.textSubsequentVisitsNextVisit.text.toString()


        if (numberOfVisit == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsRegistrationNumber.error = "Required"
        }

        if (numberOfVisit == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsNumberOfVisit.error = "Required"
        }

        if (dateOfVisit == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsDate.error = "Required"
        }
        if (urine == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsUrine.error = "Required"
        }
        if (weight == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsWeight.error = "Required"
        }
        if (bp == "") {
            mBinding.textLayoutSubsequentVisitsBp.error = "Required"
            isValid = false
        }
        if (hb == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsHb.error = "Required"
        }
        if (pallor == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsPallor.error = "Required"
        }

        if (maturity == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsMaturity.error = "Required"
        }

        if (fundalHeight == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsFundalHeight.error = "Required"
        }
        if (presentation == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsPresentation.error = "Required"
        }
        if (lie == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsLie.error = "Required"
        }
        if (foetalHeart == "") {
            isValid = false
            mBinding.textLayoutSubsequentFoetalHeart.error = "Required"
        }
        if (foetalMovement == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsFoetalMvt.error = "Required"
        }

        if (nextVisit == "") {
            isValid = false
            mBinding.textLayoutSubsequentNextVisit.error = "Required"
        }

        registrationNumber = mBinding.textSubsequentVisitsRegistrationNumber.text.toString()
        numberOfVisit = mBinding.textSubsequentVisitsNumberOfVisit.text.toString()
        dateOfVisit = mBinding.textSubsequentVisitsDate.text.toString()
        urine = mBinding.textSubsequentVisitsUrine.text.toString()
        weight = mBinding.textSubsequentVisitsWeight.text.toString()
        bp = mBinding.textSubsequentVisitsBp.text.toString()
        hb = mBinding.textSubsequentVisitsHb.text.toString()
        pallor = mBinding.textSubsequentVisitsPallor.text.toString()
        maturity = mBinding.textSubsequentVisitsMaturity.text.toString()
        fundalHeight = mBinding.textSubsequentVisitsFundalHeight.text.toString()
        presentation = mBinding.textSubsequentVisitsPresentation.text.toString()
        lie = mBinding.textSubsequentVisitsLie.text.toString()
        foetalHeart = mBinding.textSubsequentVisitsFoetalHeart.text.toString()
        foetalMovement = mBinding.textSubsequentVisitsFoetalMvt.text.toString()
        nextVisit = mBinding.textSubsequentVisitsNextVisit.text.toString()


        if (registrationNumber != "" && numberOfVisit != "" && dateOfVisit != "" && urine != "" && weight != ""
            && bp != "" && hb != "" && pallor != "" && maturity != "" && fundalHeight != ""
            && presentation != "" && lie != "" && foetalHeart != "" && presentation != "" && foetalMovement != "" && nextVisit != ""
        ) {
            isValid = true
        }

        return isValid

    }

    fun saveMedicalSurgicalHistory() {
        val subsequentVisit = ExpectantSubsequentVisit(
            registrationNumber, numberOfVisit, dateOfVisit, urine, weight,
            bp, hb, pallor, maturity, fundalHeight, presentation, lie,
            foetalHeart, foetalMovement, nextVisit
        )

        mDatabase.collection("patients")
            .document("maternalVisit")
            .collection("subsequentVisits").document(registrationNumber).set(
                subsequentVisit
            ).addOnSuccessListener {
                _writeStatus.value = true
            }.addOnFailureListener { exception ->
                _exception.value = exception
                _writeStatus.value = false

            }

    }


}