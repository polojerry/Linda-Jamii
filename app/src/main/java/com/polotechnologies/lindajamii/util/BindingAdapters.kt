package com.polotechnologies.lindajamii.util

import android.text.format.DateFormat
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import java.util.Calendar

@BindingAdapter("nextVisit")
fun bind(textView: AppCompatTextView, expectantDetails: ExpectantDetails){
    expectantDetails.nextVisit.let {nextVisit->
        if(nextVisit!=null){
            val calender = Calendar.getInstance()
            calender.timeInMillis = nextVisit

            val nextVisitDate = DateFormat.format("dd/MM/yy", calender).toString()
            textView.text = nextVisitDate
        }

    }

}


@BindingAdapter("visitBackground")
fun bind(expectantCard: MaterialCardView, expectantDetails: ExpectantDetails){
    expectantDetails.nextVisit.let {nextVisit->
        if(nextVisit!=null){
            val dateToday = Calendar.getInstance().timeInMillis

            if(dateToday > nextVisit){
                expectantCard.setBackgroundColor(R.color.colorLightRed)
            }
        }

    }

}