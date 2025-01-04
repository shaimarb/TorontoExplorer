package com.shaima.group6_comp304sec001_lab04_ex1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaima.group6_comp304sec001_lab04_ex1.database.LandmarkEntity


class LandmarkAdapter(
    private var landmarks: List<LandmarkEntity>,
    private val onClick: (LandmarkEntity) -> Unit
) : RecyclerView.Adapter<LandmarkAdapter.ViewHolder>() {

    // ViewHolder class to hold item views
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textViewLandmarkName)
        val addressTextView: TextView = view.findViewById(R.id.textViewLandmarkAddress)
        val typeTextView: TextView = view.findViewById(R.id.textViewLandmarkType)
    }

    // Inflate the item layout and create a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.landmark_item, parent, false)
        return ViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val landmark = landmarks[position]
        with(holder) {
            nameTextView.text = landmark.name // Set landmark name
            addressTextView.text = landmark.address // Set landmark address
            typeTextView.text = landmark.type // Set landmark type
            itemView.setOnClickListener { onClick(landmark) } // Handle item click
        }
    }

    // Return the number of items in the list
    override fun getItemCount() = landmarks.size

    // Update the data and refresh the view
    fun updateData(newLandmarks: List<LandmarkEntity>) {
        landmarks = newLandmarks
        notifyDataSetChanged() // Notify RecyclerView to refresh
    }
}
