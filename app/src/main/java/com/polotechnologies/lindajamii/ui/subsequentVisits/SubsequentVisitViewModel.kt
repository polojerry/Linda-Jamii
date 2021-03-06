package com.polotechnologies.lindajamii.ui.subsequentVisits


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.ExpectantSubsequentVisit
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding
import com.polotechnologies.lindajamii.repository.PatientRepository


class SubsequentVisitViewModel(
    val application: Application,
    val mBinding: FragmentSubsequentVisitsBinding
) :
    ViewModel() {

    private val patientsRepository = PatientRepository(LindaJamiiDatabase.getDatabase(application))

    private val _writeStatusLoading = MutableLiveData<Boolean>()
    val writeStatusLoading: LiveData<Boolean>
        get() = _writeStatusLoading


    private var registrationNumber = ""
    private var numberOfVisit = ""
    var dateOfVisit = 0L
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
    var nextVisit = 0L


    fun isFieldsValid(): Boolean {
        var isValid = false

        registrationNumber = mBinding.textSubsequentVisitsRegistrationNumber.text.toString()
        numberOfVisit = mBinding.textSubsequentVisitsNumberOfVisit.text.toString()
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


        if (numberOfVisit == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsRegistrationNumber.error = "Required"
        }

        if (numberOfVisit == "") {
            isValid = false
            mBinding.textLayoutSubsequentVisitsNumberOfVisit.error = "Required"
        }

        if (dateOfVisit == 0L) {
            isValid = false
            mBinding.textLayoutSubsequentVisitsDate.error = "Date Required"
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

        if (nextVisit == 0L) {
            isValid = false
            mBinding.textLayoutSubsequentNextVisit.error = "Next Visit Required"
        }

        registrationNumber = mBinding.textSubsequentVisitsRegistrationNumber.text.toString()
        numberOfVisit = mBinding.textSubsequentVisitsNumberOfVisit.text.toString()
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


        if (registrationNumber != "" && numberOfVisit != "" && dateOfVisit != 0L && urine != "" && weight != ""
            && bp != "" && hb != "" && pallor != "" && maturity != "" && fundalHeight != ""
            && presentation != "" && lie != "" && foetalHeart != "" && presentation != "" && foetalMovement != "" && nextVisit != 0L
        ) {
            isValid = true
        }

        return isValid

    }

    private fun createExpectantSubsequentVisit() : ExpectantSubsequentVisit {
        return  ExpectantSubsequentVisit(
            registrationNumber, numberOfVisit,dateOfVisit, urine, weight,
            bp, hb, pallor, maturity, fundalHeight, presentation, lie,
            foetalHeart, foetalMovement, nextVisit
        )
    }

    fun saveSubsequentVisit() = patientsRepository.saveSubsequentVisit(createExpectantSubsequentVisit())

    fun setIsLoading(isLoading: Boolean) {
        _writeStatusLoading.value = isLoading

    }

}