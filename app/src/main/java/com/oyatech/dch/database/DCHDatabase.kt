package com.oyatech.dch.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.Vitals

@Database(
    entities = [PatientBioData::class,Vitals::class,DailyVitals::class], version = 1
)
abstract class DCHDatabase : RoomDatabase() {

     abstract fun mDao(): IDao

    companion object{
        @Volatile
        private  var database: DCHDatabase? = null
        fun getDatabaseInstance(context: Application): DCHDatabase {
 val temInstant = database
            if (temInstant!=null){
                return database!!
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context,
                    DCHDatabase::class.java, "dch_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                database = instance
                return database!!
            }

        }

    }
}