package com.shaima.group6_comp304sec001_lab04_ex1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaima.group6_comp304sec001_lab04_ex1.database.LandmarkType

class LandmarkTypeAdapter(
    private val landmarkTypes: Array<LandmarkType>,
    private val onClick: (LandmarkType) -> Unit
) : RecyclerView.Adapter<LandmarkTypeAdapter.LandmarkTypeViewHolder>() {

    // Inflate the item layout and create a LandmarkTypeViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkTypeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return LandmarkTypeViewHolder(view, onClick)
    }

    // Bind data to the LandmarkTypeViewHolder
    override fun onBindViewHolder(holder: LandmarkTypeViewHolder, position: Int) {
        holder.bind(landmarkTypes[position])
    }

    // Return the number of items in the list
    override fun getItemCount() = landmarkTypes.size

    // ViewHolder class for LandmarkType items
    class LandmarkTypeViewHolder(
        itemView: View,
        private val onClick: (LandmarkType) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(landmarkType: LandmarkType) {
            nameTextView.text = landmarkType.name.replace('_', ' ') // Format the name
            itemView.setOnClickListener { onClick(landmarkType) } // Handle item click
        }
    }
}
