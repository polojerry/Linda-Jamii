package com.polotechnologies.lindajamii.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.database.typeConveters.TypeConverterDate
import com.polotechnologies.lindajamii.database.typeConveters.TypeConverterExpectantMaternalProfile
import com.polotechnologies.lindajamii.database.typeConveters.TypeConverterExpectantMedicalSurgicalHistory
import com.polotechnologies.lindajamii.database.typeConveters.TypeConverterExpectantPhysicalAntenatalFeeding

@Database(entities = [ExpectantDetails::class], version = 1, exportSchema = false)
@TypeConverters(
    TypeConverterDate::class,
    TypeConverterExpectantMaternalProfile::class,
    TypeConverterExpectantMedicalSurgicalHistory::class,
    TypeConverterExpectantPhysicalAntenatalFeeding::class

)
abstract class LindaJamiiDatabase : RoomDatabase() {

    abstract val expectantDetailsDao: ExpectantDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: LindaJamiiDatabase? = null

        fun getDatabase(context: Context): LindaJamiiDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
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