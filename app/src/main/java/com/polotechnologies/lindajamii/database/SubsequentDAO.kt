/*
package com.polotechnologies.lindajamii.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.polotechnologies.lindajamii.dataModels.PatientDetails
import com.polotechnologies.lindajamii.dataModels.SubsequentVisit

@Dao
interface SubsequentDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(subsequentVisit: SubsequentVisit?) : Long

    @Query("SELECT * FROM subsequent_visit_table WHERE registration_id LIKE :id")
    fun patient(id: Int?) : LiveData<List<PatientDetails>>

    @Query("SELECT * FROM subsequent_visit_table ORDER BY nextVisit ASC")
    fun patients() : LiveData<List<PatientDetails>>
}*/
