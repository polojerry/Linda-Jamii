package com.polotechnologies.lindajamii.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.old.PatientDetails

@Dao
interface ExpectantDetailsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg expectantDetails: ExpectantDetails)

    @Query("SELECT * FROM expectant_details_table")
    fun getPatients(): LiveData<List<ExpectantDetails>>
}