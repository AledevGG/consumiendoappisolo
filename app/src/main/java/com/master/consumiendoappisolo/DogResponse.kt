package com.master.consumiendoappisolo


import com.google.gson.annotations.SerializedName


data class DogResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var imagenes: List<String>
)
