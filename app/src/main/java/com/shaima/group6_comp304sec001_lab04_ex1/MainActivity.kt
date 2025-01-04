package com.shaima.group6_comp304sec001_lab04_ex1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaima.group6_comp304sec001_lab04_ex1.database.LandmarkType


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView and set its layout manager
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLandmarkTypes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get all landmark types and create an adapter with a click listener
        val landmarkTypes = LandmarkType.values()
        val adapter = LandmarkTypeAdapter(landmarkTypes) { landmarkType ->
            // Create an Intent to start LandmarksActivity with the selected landmark type
            val intent = Intent(this, LandmarksActivity::class.java).apply {
                putExtra(LandmarksActivity.EXTRA_LANDMARK_TYPE, landmarkType.name)
            }
            startActivity(intent)
        }
        // Set the adapter to the RecyclerView
        recyclerView.adapter = adapter
    }
}
