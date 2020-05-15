package com.polotechnologies.lindajamii.util

import android.graphics.Color
import android.text.format.DateFormat
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.google.firebase.database.collection.LLRBNode
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import java.util.Calendar

@BindingAdapter("nextVisit")
fun bind(textView: AppCompatTextView, expectantDetails: ExpectantDetails) {
    expectantDetails.nextVisit.let { nextVisit ->
        if (nextVisit != null) {
            textView.text = DateConverter.getStringDate(nextVisit)
        }
    }

}

@BindingAdapter("visitBackground")
fun bind(constraintLayout: ConstraintLayout, expectantDetails: ExpectantDetails) {
    expectantDetails.nextVisit.let { nextVisit ->
        constraintLayout.setBackgroundColor(Color.WHITE)
        if (nextVisit != null) {
            val dateToday = Calendar.getInstance().timeInMillis

            if (dateToday > nextVisit) {
                constraintLayout.setBackgroundColor(Color.RED)
            } else {
                constraintLayout.setBackgroundColor(Color.WHITE)
            }
        }

    }

}