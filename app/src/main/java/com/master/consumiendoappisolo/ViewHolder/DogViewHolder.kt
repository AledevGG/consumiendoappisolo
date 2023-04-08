package com.master.consumiendoappisolo.ViewHolder

import android.media.Image
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.master.consumiendoappisolo.databinding.ItemDogsBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val binding = ItemDogsBinding.bind(view)




    fun bind(image: String) {

        Picasso.get().load(image).into(binding.ivDog)

    }

}