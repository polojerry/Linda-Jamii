package com.polotechnologies.lindajamii.util

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.ui.patientsDetails.PatientDetailsFragmentArgs

object ExpectantVisitNotification {

    private const val NOTIFICATION_TAG = "expectantReminder"
    const val NOTIFICATION_CHANNEL = "expectant_reminder"
    private const val NOTIFICATION_TITTLE = "Expectant Next Visit"
    fun notify(
        context: Context,
        patientName: String, visitDate: String, ancNumber : String) {

        val notificationMessage = "$patientName visit is scheduled for $visitDate"
        val args = PatientDetailsFragmentArgs.Builder(ancNumber).build().toBundle()
        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.nav_graph_main)
            .setDestination(R.id.patientsDetailsFragment)
            .setArguments(args)
            .createPendingIntent()


        val builder = NotificationCompat.Builder(context,NOTIFICATION_CHANNEL)
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.drawable.linda_jamii_logo_white)
            .setContentTitle(NOTIFICATION_TITTLE)
            .setContentText(notificationMessage)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setTicker(NOTIFICATION_TITTLE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notify(context, builder.build())

    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private fun notify(context: Context, notification: Notification) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(NOTIFICATION_TAG, 0, notification)
    }

}