package com.master.consumiendoappisolo.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.master.consumiendoappisolo.R
import com.master.consumiendoappisolo.ViewHolder.DogViewHolder

class DogAdapter(private val images: List<String>): RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
    val layout = LayoutInflater.from(parent.context)

        return DogViewHolder(layout.inflate(R.layout.item_dogs, parent, false))
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(images[position])
    }
}