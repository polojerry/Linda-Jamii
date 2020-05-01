package com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding

class MedicalSurgicalHistoryViewModel(
    val mDatabase: FirebaseFirestore,
    val mBinding: FragmentMedicalSurgicalHistoryBinding
) : ViewModel() {

    private var surgicalOperation = ""
    private var diabetes = ""
    private var hypertension = ""
    private var bloodTransfusion = ""
    private var tuberculosis = ""
    private var specificDrugAllergy = ""
    private var otherDrugAllergies = ""
    private var familyHistoryTwins = ""
    private var familyHistoryTuberculosis = ""


    fun isFieldsValid(): Boolean {
        var isValid = false

        surgicalOperation = mBinding.textMedicalSurgicalHistorySurgicalOperationSpecific.text.toString()
        diabetes = mBinding.textMedicalSurgicalHistoryDiabetes.text.toString()
        hypertension = mBinding.textMedicalSurgicalHistoryHypertension.text.toString()
        bloodTransfusion = mBinding.textMedicalSurgicalHistoryHypertension.text.toString()
        tuberculosis = mBinding.textMedicalSurgicalHistoryTuberculosis.text.toString()
        specificDrugAllergy = mBinding.textMedicalSurgicalHistoryDrugAllergy.text.toString()
        otherDrugAllergies = mBinding.textMedicalSurgicalHistoryOthersDrugAllergySpecify.text.toString()
        familyHistoryTwins = mBinding.textMedicalSurgicalHistoryTwins.text.toString()
        familyHistoryTuberculosis = mBinding.textMedicalSurgicalHistoryFamilyHistoryTuberculosis.text.toString()


        if (surgicalOperation == ""){
            isValid = false
            mBinding.textLayoutMedicalSurgicalHistorySergicalOperation.error = "Required"
        }

        if (diabetes == "") {
            isValid = false
            mBinding.textLayoutMedicalSurgicalHistoryDiabetes.error = "Required"
        }
        if (hypertension == "") {
            isValid = false
            mBinding.textLayoutMedicalSurgicalHistoryHyperTension.error = "Required"
        }
        if (bloodTransfusion == "") {
            isValid = false
            mBinding.textLayoutMedicalSurgicalHistoryBloodTransfusion.error = "Required"
        }
        if (tuberculosis == "") {
            mBinding.textLayoutMedicalSurgicalHistoryTuberculosis.error = "Required"
            isValid = false
        }
        if (specificDrugAllergy == "") {
            isValid = false
            mBinding.textLayoutMedicalSurgicalHistoryDrugAllergy.error = "Required"
        }
        if (otherDrugAllergies == "") {
            isValid = false
            mBinding.textLayoutMedicalSurgicalHistoryOthersDrugAllergySpecify.error = "Required"
        }

        if (familyHistoryTwins == "") {

            isValid = false
            mBinding.textLayoutMedicalSurgicalHistoryTwins.error = "Required"
        }

        if (familyHistoryTuberculosis == "") {
            isValid = false
            mBinding.textLayoutMedicalSurgicalHistoryFamilyHistoryTuberculosis.error = "Required"
        }

        if (surgicalOperation != "" && diabetes != "" && hypertension != "" && bloodTransfusion != "" && tuberculosis != ""
            && specificDrugAllergy != "" && otherDrugAllergies != "" && familyHistoryTwins != "" && familyHistoryTuberculosis != ""
        ) {
            isValid = true
        }

        return isValid

    }


}