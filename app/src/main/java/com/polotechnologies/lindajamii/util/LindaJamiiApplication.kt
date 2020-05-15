package com.polotechnologies.lindajamii.util

import android.app.Application
import android.os.Build
import androidx.work.*
import com.polotechnologies.lindajamii.work.RefreshPatientsWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class LindaJamiiApplication : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        scheduleRefreshPatients()
    }

    private fun scheduleRefreshPatients() {
        applicationScope.launch {
            setUpSchedule()
        }
    }

    private fun setUpSchedule() {

        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .apply {
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                    setRequiresDeviceIdle(true)
                }
            }.build()


        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshPatientsWork>(
            12,
            TimeUnit.HOURS
        ).setConstraints(constraints).build()


        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshPatientsWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }

}