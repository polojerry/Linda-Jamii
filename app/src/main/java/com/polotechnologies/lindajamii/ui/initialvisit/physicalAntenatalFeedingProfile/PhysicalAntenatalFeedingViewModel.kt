package com.polotechnologies.lindajamii.ui.initialvisit.physicalAntenatalFeedingProfile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.databinding.FragmentPhysicalAntenatalFeedingBinding
import com.polotechnologies.lindajamii.repository.PatientRepository
class PhysicalAntenatalFeedingViewModel(
    val application: Application,
    val mBinding: FragmentPhysicalAntenatalFeedingBinding,
    private val mUserId :String
) : ViewModel() {

    private val patientsRepository = PatientRepository(LindaJamiiDatabase.getDatabase(application))

    private val _writeStatusLoading = MutableLiveData<Boolean>()
    val writeStatusLoading: LiveData<Boolean>
        get() = _writeStatusLoading


    //Physical Examination
    private var general = ""
    private var bp = ""
    private var height = ""
    private var cvs = ""
    private var resp = ""
    private var breasts = ""
    private var abdomen = ""
    private var vaginalExamination = ""
    private var dischargeGenitalUlcers = ""

    //Antenatal Profile
    private var hb = ""
    private var bloodGroup = ""
    private var rhesus = ""
    private var serology = ""
    private var tbScreening = ""

    //To be Added
    private var dateIPTIsonaziadGiven = ""
    private var nextVisit = ""


    private var hiv = ""
    private var urianalysis = ""
    private var givenHIVCounsellingAndTest = ""

    //Infant Feeding
    private var feedingCounsellingDone = ""
    private var counselingOnExclusiveBreastfeedingDone = ""


    fun isFieldsValid(): Boolean {
        var isValid = false

        general = mBinding.textPhysicalExaminationGeneral.text.toString()
        bp = mBinding.textPhysicalExaminationBp.text.toString()
        height = mBinding.textPhysicalExaminationHeight.text.toString()
        cvs = mBinding.textPhysicalExaminationCvs.text.toString()
        resp = mBinding.textPhysicalExaminationResp.text.toString()
        breasts = mBinding.textPhysicalExaminationBreast.text.toString()
        abdomen = mBinding.textPhysicalExaminationAbdomen.text.toString()
        vaginalExamination = mBinding.textPhysicalExaminationVaginalExamination.text.toString()
        dischargeGenitalUlcers =
            mBinding.textPhysicalExaminationDischargeGenitalUlcer.text.toString()

        hb = mBinding.textAntenatalProfileHb.text.toString()
        bloodGroup = mBinding.textAntenatalProfileBloodGroup.text.toString()
        rhesus = mBinding.textAntenatalProfileRhesusFactor.text.toString()
        serology = mBinding.textAntenatalProfileSerelogy.text.toString()
        tbScreening = mBinding.textAntenatalProfileTbScreening.text.toString()
        hiv = mBinding.textAntenatalProfileHiv.text.toString()
        urianalysis = mBinding.textAntenatalProfileUrinalysis.text.toString()
        givenHIVCounsellingAndTest =
            mBinding.textAntenatalProfileHivCounsellingAndTesting.text.toString()

        feedingCounsellingDone = mBinding.textInfantFeedingCounselling.text.toString()
        counselingOnExclusiveBreastfeedingDone =
            mBinding.textInfantFeedingCounselingBreastfeeding.text.toString()


        if (general == "") {
            isValid = false
            mBinding.textLayoutPhysicalExaminationGeneral.error = "Required"
        }

        if (bp == "") {
            isValid = false
            mBinding.textLayoutPhysicalExaminationBp.error = "Required"
        }
        if (height == "") {
            isValid = false
            mBinding.textLayoutPhysicalExaminationHeight.error = "Required"
        }
        if (cvs == "") {
            isValid = false
            mBinding.textLayoutPhysicalExaminationCvs.error = "Required"
        }
        if (resp == "") {
            mBinding.textLayoutPhysicalExaminationResp.error = "Required"
            isValid = false
        }
        if (breasts == "") {
            isValid = false
            mBinding.textLayoutPhysicalExaminationBreast.error = "Required"
        }
        if (abdomen == "") {
            isValid = false
            mBinding.textLayoutPhysicalExaminationAbdomen.error = "Required"
        }

        if (vaginalExamination == "") {
            isValid = false
            mBinding.textLayoutPhysicalExaminationVaginalExamination.error = "Required"
        }

        if (dischargeGenitalUlcers == "") {
            isValid = false
            mBinding.textLayoutPhysicalExaminationDischargeGenitalUlcer.error = "Required"
        }

        //Antenatal Profile
        if (hb == "") {
            isValid = false
            mBinding.textLayoutAntenatalProfileHb.error = "Required"
        }
        if (bloodGroup == "") {
            isValid = false
            mBinding.textLayoutAntenatalProfileBloodGroup.error = "Required"
        }
        if (rhesus == "") {
            isValid = false
            mBinding.textLayoutAntenatalProfileRhesusFactor.error = "Required"
        }
        if (serology == "") {
            isValid = false
            mBinding.textLayoutAntenatalProfileSerelogy.error = "Required"
        }
        if (tbScreening == "") {
            mBinding.textLayoutAntenatalProfileTbScreening.error = "Required"
            isValid = false
        }
        if (hiv == "") {
            isValid = false
            mBinding.textLayoutAntenatalProfileHiv.error = "Required"
        }
        if (urianalysis == "") {
            isValid = false
            mBinding.textLayoutAntenatalProfileUrinalysis.error = "Required"
        }

        if (givenHIVCounsellingAndTest == "") {

            isValid = false
            mBinding.textLayoutAntenatalProfileHivCounsellingAndTesting.error = "Required"
        }

        //Infant Feeding
        if (feedingCounsellingDone == "") {
            isValid = false
            mBinding.textLayoutInfantFeedingCounsellingDone.error = "Required"
        }

        if (counselingOnExclusiveBreastfeedingDone == "") {
            isValid = false
            mBinding.textLayoutInfantFeedingCounsellingBreastfeeding.error = "Required"
        }



        if (general != "" && bp != "" && height != "" && cvs != "" && resp != "" && breasts != ""
            && abdomen != "" && vaginalExamination != "" && vaginalExamination != "" && dischargeGenitalUlcers != ""

            && hb != "" && bloodGroup != "" && rhesus != "" && serology != "" && tbScreening != ""
            && hiv != "" && urianalysis != "" && givenHIVCounsellingAndTest != ""

            && feedingCounsellingDone != "" && counselingOnExclusiveBreastfeedingDone != ""
        ) {
            isValid = true
        }

        return isValid
    }

    private fun createPhysicalAntenatalFeeding() : ExpectantPhysicalAntenatalFeeding{
        return ExpectantPhysicalAntenatalFeeding(
            general, bp, height, cvs, resp,
            breasts, abdomen, vaginalExamination, dischargeGenitalUlcers,
            hb, bloodGroup, rhesus, serology, tbScreening, dateIPTIsonaziadGiven,
            nextVisit, hiv, urianalysis, givenHIVCounsellingAndTest, feedingCounsellingDone,
            counselingOnExclusiveBreastfeedingDone
            )
    }

    fun savePhysicalAntenatalFeeding() =
        patientsRepository.saveInitialVisitPhysicalAntenatal(mUserId, createPhysicalAntenatalFeeding())

    fun setIsLoading(isLoading: Boolean) {
        _writeStatusLoading.value = isLoading

    }

}