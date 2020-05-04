package com.polotechnologies.lindajamii.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.old.PatientDetails
import com.polotechnologies.lindajamii.database.typeConveters.newConverters.TypeConverterExpectantMaternalProfile
import com.polotechnologies.lindajamii.database.typeConveters.newConverters.TypeConverterExpectantMedicalSurgicalHistory
import com.polotechnologies.lindajamii.database.typeConveters.newConverters.TypeConverterExpectantPhysicalAntenatalFeeding
import com.polotechnologies.lindajamii.database.typeConveters.old.AntenatalProfileConverter
import com.polotechnologies.lindajamii.database.typeConveters.old.MaternalProfileConverter
import com.polotechnologies.lindajamii.database.typeConveters.old.MedicalSurgicalHistoryConverter
import com.polotechnologies.lindajamii.database.typeConveters.old.PhysicalExaminationConverter

@Database(entities = [ExpectantDetails::class], version = 1, exportSchema = false )
@TypeConverters(
    TypeConverterExpectantMaternalProfile::class, TypeConverterExpectantMedicalSurgicalHistory::class,
TypeConverterExpectantPhysicalAntenatalFeeding::class)
abstract class LindaJamiiDatabase : RoomDatabase() {

    abstract val expectantDetailsDao: ExpectantDetailsDao

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