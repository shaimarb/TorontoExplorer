package com.shaima.group6_comp304sec001_lab04_ex1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaima.group6_comp304sec001_lab04_ex1.viewmodels.LandmarkViewModel


class LandmarksActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: LandmarkViewModel
    private lateinit var landmarkAdapter: LandmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmarks)

        // Set the toolbar as the support action bar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[LandmarkViewModel::class.java]
        recyclerView = findViewById(R.id.recyclerViewLandmarks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter
        landmarkAdapter = LandmarkAdapter(emptyList()) { selectedLandmark ->
            Log.d("LandmarksActivity", "Landmark selected: ${selectedLandmark.name}")
            val intent = Intent(this, DetailLandmarkActivity::class.java).apply {
                putExtra("EXTRA_LANDMARK", selectedLandmark)
            }
            startActivity(intent)
        }

        recyclerView.adapter = landmarkAdapter

        // Retrieve the landmark type from the Intent extras and observe the LiveData from ViewModel
        intent.getStringExtra(EXTRA_LANDMARK_TYPE)?.let { typeName ->
            // Set the toolbar title to the landmark type
            supportActionBar?.title = typeName

            viewModel.getLandmarksByType(typeName).observe(this, { landmarks ->
                landmarkAdapter.updateData(landmarks)
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Handle the back arrow click here
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_LANDMARK_TYPE = "EXTRA_LANDMARK_TYPE"
    }
}
