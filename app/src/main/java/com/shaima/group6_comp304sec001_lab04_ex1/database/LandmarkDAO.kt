package com.shaima.group6_comp304sec001_lab04_ex1.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface LandmarkDao {
    @Query("SELECT * FROM landmarks WHERE type = :type")
    fun getLandmarksByType(type: String): LiveData<List<LandmarkEntity>>
}