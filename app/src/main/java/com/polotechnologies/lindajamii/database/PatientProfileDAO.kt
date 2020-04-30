package com.polotechnologies.lindajamii.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.polotechnologies.lindajamii.dataModels.PatientDetails

@Dao
interface PatientProfileDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(patientDetails: PatientDetails) : Long

    @Query("SELECT * FROM patients_details_table WHERE patients_number LIKE :patientNumber")
    fun patient(patientNumber: Int?) : LiveData<List<PatientDetails>>

    @Query("SELECT * FROM patients_details_table ORDER BY patients_number ASC")
    fun patients() : LiveData<List<PatientDetails>>

}