package com.polotechnologies.lindajamii.util

import android.graphics.Color
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.HomeOption
import java.util.Calendar

@BindingAdapter("imageSrc")
fun bind(imageView: AppCompatImageView, homeOption: HomeOption){
    homeOption.option_image.let {image->
    Glide.with(imageView.context)
        .load(image)
        .into(imageView)
        }
}

@BindingAdapter("nextVisit")
fun bind(textView: AppCompatTextView, expectantDetails: ExpectantDetails) {
    expectantDetails.nextVisit.let { nextVisit ->
        if (nextVisit != null) {
            textView.text = DateConverter.getStringDate(nextVisit)
        }
    }

}

@BindingAdapter("age")
fun bind(textView: AppCompatTextView, age: String){
    age.let { ageString->
        textView.text = textView.context.getString(R.string.string_format_age,ageString)
    }
}

@BindingAdapter("ancNumber")
fun bindText(textView: AppCompatTextView, ancNumber: String){
    ancNumber.let { anc->
        textView.text = textView.context.getString(R.string.string_format_anc_number,anc)
    }
}

@BindingAdapter("edd")
fun bindEdd(textView: AppCompatTextView, edd: String){
    edd.let { eDelivery->
        textView.text = textView.context.getString(R.string.string_format_edd,eDelivery)
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