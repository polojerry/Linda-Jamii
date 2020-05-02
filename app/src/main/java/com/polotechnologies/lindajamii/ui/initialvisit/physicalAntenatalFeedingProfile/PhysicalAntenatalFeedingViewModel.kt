package com.polotechnologies.lindajamii.ui.initialvisit.physicalAntenatalFeedingProfile

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding
import com.polotechnologies.lindajamii.databinding.FragmentPhysicalAntenatalFeedingBinding

class PhysicalAntenatalFeedingViewModel(
    val mDatabase: FirebaseFirestore,
    val mBinding: FragmentPhysicalAntenatalFeedingBinding
) : ViewModel() {

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

        general = mBinding.textPysicalExaminationGeneral.text.toString()
        bp = mBinding.textPysicalExaminationBp.text.toString()
        height = mBinding.textPysicalExaminationHeight.text.toString()
        cvs = mBinding.textPysicalExaminationCvs.text.toString()
        resp = mBinding.textPysicalExaminationResp.text.toString()
        breasts = mBinding.textPysicalExaminationBreast.text.toString()
        abdomen = mBinding.textPysicalExaminationAbdomen.text.toString()
        vaginalExamination = mBinding.textPysicalExaminationVaginalExamination.text.toString()
        dischargeGenitalUlcers =
            mBinding.textPysicalExaminationDischargeGenitalUlcer.text.toString()

        hb = mBinding.textAntenatalProfileHb.text.toString()
        bloodGroup = mBinding.textAntenatalProfileBloodGroup.text.toString()
        rhesus = mBinding.textAntenatalProfileRhesusFactor.text.toString()
        serology = mBinding.textAntenatalProfileSerelogy.text.toString()
        tbScreening = mBinding.textAntenatalProfileTbScreeening.text.toString()
        //dateIPTIsonaziadGiven = mBinding..text.toString()
        //nextVisit = mBinding.te.text.toString()
        hiv = mBinding.textAntenatalProfileHiv.text.toString()
        urianalysis = mBinding.textAntenatalProfileUrinalisis.text.toString()
        givenHIVCounsellingAndTest =
            mBinding.textAntenatalProfileHivCouselingAndTesting.text.toString()

        feedingCounsellingDone = mBinding.textInfantFeedingCouseling.text.toString()
        counselingOnExclusiveBreastfeedingDone =
            mBinding.textInfantFeedingCouselingBreastfeeding.text.toString()


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
            mBinding.textLayoutAntenatalProfileTbScrening.error = "Required"
            isValid = false
        }
        if (hiv == "") {
            isValid = false
            mBinding.textLayoutAntenatalProfileHiv.error = "Required"
        }
        if (urianalysis == "") {
            isValid = false
            mBinding.textLayoutAntenatalProfileUrinalisis.error = "Required"
        }

        if (givenHIVCounsellingAndTest == "") {

            isValid = false
            mBinding.textLayoutAntenatalProfileHivCouselingAndTesting.error = "Required"
        }

        //Infant Feeding
        if (feedingCounsellingDone == "") {
            isValid = false
            mBinding.textLayoutInfantFeedingCouselingDone.error = "Required"
        }

        if (counselingOnExclusiveBreastfeedingDone == "") {
            isValid = false
            mBinding.textLayoutInfantFeedingCouselingBreastfeeding.error = "Required"
        }



        if (general != "" && bp != "" && height != "" && cvs != "" && resp != "" && breasts != ""
            && abdomen != "" && vaginalExamination != "" && vaginalExamination != "" && dischargeGenitalUlcers != ""

            && hb != "" && bloodGroup != "" && rhesus != "" && serology != "" && tbScreening != ""
            && dateIPTIsonaziadGiven != "" && nextVisit != "" && hiv != "" && urianalysis != "" && givenHIVCounsellingAndTest != ""

            && feedingCounsellingDone != "" && counselingOnExclusiveBreastfeedingDone != ""
        ) {
            isValid = true
        }

        return isValid
    }

    fun savePhysicalAntenatalFeeding() {
        val physicalAntenatalFeeding = ExpectantPhysicalAntenatalFeeding(
            general, bp, height, cvs, resp,
            breasts, abdomen, vaginalExamination, dischargeGenitalUlcers,
            hb, bloodGroup, rhesus, serology, tbScreening, dateIPTIsonaziadGiven,
            nextVisit, hiv, urianalysis, givenHIVCounsellingAndTest, feedingCounsellingDone,
            counselingOnExclusiveBreastfeedingDone
            )
    }


}