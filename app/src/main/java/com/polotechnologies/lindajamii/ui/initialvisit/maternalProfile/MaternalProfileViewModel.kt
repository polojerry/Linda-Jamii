package com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import java.util.UUID

class MaternalProfileViewModel(
    val mDatabase: FirebaseFirestore,
    val mBinding: FragmentMaternalProfileBinding
) : ViewModel() {

    private val _writeStatus = MutableLiveData<Boolean>()
    val writeStatus: LiveData<Boolean>
        get() = _writeStatus

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    private var nameOfInstitution = ""
    private var mflNumber = ""
    private var ancNumber = ""
    private var pncNumber = ""
    private var nameOfPatient = ""
    private var ageOfPatient = ""
    private var gravida = ""
    private var parity = ""
    private var height = ""
    private var weight = ""
    private var lmp = ""
    private var edd = ""
    private var maritalStatus = ""
    private var education = ""
    private var address = ""
    private var telephone = ""
    private var nextOfKin = ""
    private var relationShip = ""
    private var nextOfKinContact = ""


    fun isFieldsValid(): Boolean {
        var isValid = false

        nameOfInstitution = mBinding.textMaternalProfileNameOfInstitution.text.toString()
        mflNumber = mBinding.textMaternalProfileMfNumber.text.toString()
        ancNumber = mBinding.textMaternalProfileAncNumber.text.toString()
        pncNumber = mBinding.textMaternalProfilePncNumber.text.toString()
        nameOfPatient = mBinding.textMaternalProfileNameOfClient.text.toString()
        ageOfPatient = mBinding.textMaternalProfileAge.text.toString()
        gravida = mBinding.textMaternalProfileGravida.text.toString()
        parity = mBinding.textMaternalProfileParity.text.toString()
        height = mBinding.textMaternalProfileParity.text.toString()
        weight = mBinding.textMaternalProfileHeight.text.toString()
        lmp = mBinding.textMaternalProfileLmp.text.toString()
        edd = mBinding.textMaternalProfileEdd.text.toString()
        maritalStatus = mBinding.textMaternalProfileMaritalStatus.text.toString()
        education = mBinding.textMaternalProfileAddress.text.toString()
        address = mBinding.textMaternalProfileAddress.text.toString()
        telephone = mBinding.textMaternalProfileTelephone.text.toString()
        nextOfKin = mBinding.textMaternalProfileNextOfKin.text.toString()
        relationShip = mBinding.textMaternalProfileRelationship.text.toString()
        nextOfKinContact = mBinding.textLayoutProfileContactPhone.text.toString()


        if (nameOfInstitution == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileInstitutionName.error = "Required"
        }

        if (nameOfPatient == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileMflNumber.error = "Required"
            isValid = false
        }
        if (ancNumber == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileAncNumber.error = "Required"
        }
        if (pncNumber == "") {
            isValid = false
            mBinding.textLayoutMaternalProfilePncNumber.error = "Required"
        }
        if (nameOfPatient == "") {
            mBinding.textLayoutMaternalProfileClientName.error = "Required"
            isValid = false
        }
        if (ageOfPatient == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileAge.error = "Required"
        }
        if (gravida == "") {

            isValid = false
            mBinding.textLayoutMaternalProfileGravida.error = "Required"
        }
        if (parity == "") {

            isValid = false
            mBinding.textLayoutMaternalProfileParity.error = "Required"
        }
        if (height == "") {

            isValid = false
            mBinding.textLayoutMaternalProfileHeight.error = "Required"
        }
        if (weight == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileWeight.error = "Required"

        }
        if (lmp == "") {

            isValid = false
            mBinding.textLayoutMaternalProfileLmp.error = "Required"
        }
        if (edd == "") {

            isValid = false
            mBinding.textLayoutMaternalProfileEdd.error = "Required"
        }
        if (maritalStatus == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileMaritalStatus.error = "Required"
        }
        if (education == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileEducation.error = "Required"
        }
        if (address == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileAddress.error = "Required"
        }
        if (telephone == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileTelephone.error = "Required"
        }
        if (nextOfKin == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileNextOfKin.error = "Required"
        }
        if (relationShip == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileRelationship.error = "Required"
        }
        if (nextOfKinContact == "") {
            isValid = false
            mBinding.textLayoutMaternalProfileNextOfKinContactPhone.error = "Required"


        }

        if (nameOfInstitution != "" && mflNumber != "" && ancNumber != "" && pncNumber != "" && nameOfPatient != ""
            && ageOfPatient != "" && gravida != "" && parity != "" && height != "" && weight != "" && lmp != ""
            && edd != "" && maritalStatus != "" && education != "" && address != "" && telephone != "" && nextOfKin != ""
            && relationShip != "" && nextOfKinContact != ""
        ) {
            isValid = true
        }

        return isValid

    }

    fun saveMaternalProfile() {
        _userId.value= UUID.randomUUID().toString()
        val expectantDetails = ExpectantDetails(
            userId.value!!,
            null,
            getExpectantMotherProfile(),
            null,
            null
        )
        mDatabase.collection("patients").document(userId.value!!).set(expectantDetails)
            .addOnSuccessListener {
                _writeStatus.value = true
            }.addOnFailureListener{exception->
                _exception.value = exception
                _writeStatus.value = false

            }


    }

    private fun getExpectantMotherProfile(): ExpectantMaternalProfile? {

        return ExpectantMaternalProfile(
            nameOfInstitution, mflNumber, ancNumber, pncNumber,
            nameOfPatient, ageOfPatient, gravida, parity,
            height, weight, lmp, edd, maritalStatus, education,
            address, telephone, nextOfKin, relationShip, nextOfKinContact
        )
    }


}