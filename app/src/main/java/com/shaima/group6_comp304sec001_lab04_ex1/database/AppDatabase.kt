package com.shaima.group6_comp304sec001_lab04_ex1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LandmarkEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun landmarkDao(): LandmarkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Build database instance from asset file
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "landmark_database")
                    .createFromAsset("database/landmark.db") // Load pre-populated database
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}

