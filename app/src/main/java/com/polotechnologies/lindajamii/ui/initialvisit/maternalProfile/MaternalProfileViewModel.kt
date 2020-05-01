package com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding

class MaternalProfileViewModel(
    val mDatabase: FirebaseFirestore,
    val mBinding: FragmentMaternalProfileBinding
) : ViewModel() {

    private var nameOfInstitution = ""
    private var mflNumber = ""
    private var ancNumber = ""
    private var pncNumber = ""
    private var nameOfClient = ""
    private var ageOfClient = ""
    private var gravida = ""
    private var parity = ""
    private var height = ""
    private var weight = ""
    private var lmp = ""
    private var edd = ""
    private var maritalStatus = ""
    private var education = ""
    private var telephone = ""
    private var nextOfKin = ""
    private var relationShip = ""
    private var nextOfKinContact = ""


    fun isFieldsEmpty(): Boolean {
        var isValid = false

        nameOfInstitution = mBinding.textMaternalProfileNameOfInstitution.text.toString()
        mflNumber = mBinding.textMaternalProfileMfNumber.text.toString()
        ancNumber = mBinding.textMaternalProfileAncNumber.text.toString()
        pncNumber = mBinding.textMaternalProfilePncNumber.text.toString()
        nameOfClient = mBinding.textMaternalProfileNameOfClient.text.toString()
        ageOfClient = mBinding.textMaternalProfileAge.text.toString()
        gravida = mBinding.textMaternalProfileGravida.text.toString()
        parity =mBinding.textMaternalProfileParity.text.toString()
        height = mBinding.textMaternalProfileParity.text.toString()
        weight = mBinding.textMaternalProfileHeight.text.toString()
        lmp = mBinding.textMaternalProfileLmp.text.toString()
        edd = mBinding.textMaternalProfileEdd.text.toString()
        maritalStatus = mBinding.textMaternalProfileMaritalStatus.text.toString()
        education = mBinding.textMaternalProfileAddress.text.toString()
        telephone =mBinding.textMaternalProfileTelephone.text.toString()
        nextOfKin = mBinding.textMaternalProfileNextOfKin.text.toString()
        relationShip = mBinding.textMaternalProfileRelationship.text.toString()
        nextOfKinContact =mBinding.textLayoutProfileContactPhone.text.toString()


        if (nextOfKinContact == "")
            mBinding.textLayoutMaternalProfileInstitutionName.error = "Required"

        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileMflNumber.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileAncNumber.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfilePncNumber.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileClientName.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileAge.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileGravida.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileParity.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileHeight.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileWeight.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileLmp.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileEdd.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileMaritalStatus.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileEducation.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileAddress.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileTelephone.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileNextOfKin.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textMaternalProfileRelationship.error = "Required"
        }
        if (nextOfKinContact == "") {
            mBinding.textLayoutMaternalProfileNextOfKinContactPhone.error = "Required"

        }

        isValid = true

        return isValid

    }


}