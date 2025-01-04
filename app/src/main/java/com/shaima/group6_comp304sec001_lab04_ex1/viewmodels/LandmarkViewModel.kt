package com.shaima.group6_comp304sec001_lab04_ex1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shaima.group6_comp304sec001_lab04_ex1.database.AppDatabase
import com.shaima.group6_comp304sec001_lab04_ex1.database.LandmarkEntity

class LandmarkViewModel(application: Application) : AndroidViewModel(application) {
    private val landmarkDao = AppDatabase.getDatabase(application).landmarkDao()

    fun getLandmarksByType(type: String): LiveData<List<LandmarkEntity>> {
        return landmarkDao.getLandmarksByType(type)
    }
}