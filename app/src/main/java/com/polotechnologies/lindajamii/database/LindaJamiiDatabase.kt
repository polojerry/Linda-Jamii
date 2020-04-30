package com.polotechnologies.lindajamii.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.polotechnologies.lindajamii.dataModels.PatientDetails
import com.polotechnologies.lindajamii.dataModels.typeConveters.AntenatalProfileConverter
import com.polotechnologies.lindajamii.dataModels.typeConveters.MaternalProfileConverter
import com.polotechnologies.lindajamii.dataModels.typeConveters.MedicalSurgicalHistoryConverter
import com.polotechnologies.lindajamii.dataModels.typeConveters.PhysicalExaminationConverter

@Database(entities = [PatientDetails::class], version = 1, exportSchema = false )
@TypeConverters(MaternalProfileConverter::class, MedicalSurgicalHistoryConverter::class, PhysicalExaminationConverter::class,
    AntenatalProfileConverter::class)
abstract class LindaJamiiDatabase : RoomDatabase() {

    abstract val patientProfileDAO: PatientProfileDAO

    companion object{
        @Volatile
        private var INSTANCE : LindaJamiiDatabase? = null

        fun getInstance(context: Context) : LindaJamiiDatabase {
            synchronized(this){
                var instance =
                    INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LindaJamiiDatabase::class.java,
                        "linda_jamii_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance

            }
        }

    }
}