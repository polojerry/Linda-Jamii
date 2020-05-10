package com.polotechnologies.lindajamii.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.FirebaseFirestoreException
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase.Companion.getDatabase
import com.polotechnologies.lindajamii.repository.PatientRepository

class RefreshPatientsWork(val context: Context, val params: WorkerParameters) :
    CoroutineWorker(context, params) {

    companion object{
        const val WORK_NAME = "RefreshPatientsWork"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(context)
        val repository = PatientRepository(database)

        return try{
            repository.refreshPatients()
            Result.success()
        }catch (exception: FirebaseFirestoreException){
            return Result.retry()
        }
    }
}