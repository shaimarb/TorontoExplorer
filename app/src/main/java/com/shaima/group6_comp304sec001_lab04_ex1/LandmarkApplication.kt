package com.shaima.group6_comp304sec001_lab04_ex1

import android.app.Application
import com.shaima.group6_comp304sec001_lab04_ex1.database.AppDatabase

class LandmarkApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}