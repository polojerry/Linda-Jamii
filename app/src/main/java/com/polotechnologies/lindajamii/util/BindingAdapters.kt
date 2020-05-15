package com.polotechnologies.lindajamii.util

import android.text.format.DateFormat
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import java.util.Calendar
import java.util.Locale

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