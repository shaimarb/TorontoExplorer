package com.shaima.group6_comp304sec001_lab04_ex1

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shaima.group6_comp304sec001_lab04_ex1.database.LandmarkEntity
import com.shaima.group6_comp304sec001_lab04_ex1.databinding.ActivityDetailLandmarkBinding
import java.io.IOException

class DetailLandmarkActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var landmark: LandmarkEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_landmark)

        // Enable the up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Remove the title from the toolbar
        supportActionBar?.title = ""

        // Retrieve LandmarkEntity from the Intent
        landmark = intent.getParcelableExtra("EXTRA_LANDMARK") ?: return

        // Initialize the map fragment and set up the map
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        val address = landmark.address
        val geocoder = Geocoder(this)
        try {
            // Convert address to LatLng and update the map
            val addressList = geocoder.getFromLocationName(address, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val location = addressList[0]
                val landmarkLatLng = LatLng(location.latitude, location.longitude)
                map.addMarker(MarkerOptions().position(landmarkLatLng).title(landmark.name))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(landmarkLatLng, 15f))
            }
        } catch (e: IOException) {
            e.printStackTrace() // Handle geocoding error
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_map_type, menu) // Inflate menu options
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Handle the back arrow click here
                finish() // Close the activity
                true
            }
            R.id.action_normal_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NORMAL // Switch to normal map
                true
            }
            R.id.action_satellite_map -> {
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE // Switch to satellite map
                true
            }
            else -> super.onOptionsItemSelected(item) // Handle other menu items
        }
    }
}