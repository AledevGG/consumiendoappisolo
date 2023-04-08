package com.master.consumiendoappisolo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.master.consumiendoappisolo.Adapter.DogAdapter
import com.master.consumiendoappisolo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity(), OnQueryTextListener {

    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.svPrimero1.setOnQueryTextListener(this)
        initRecyclerView()


    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.rvPrimero.layoutManager = LinearLayoutManager(this)
        binding.rvPrimero.adapter = adapter

    }

    private fun getRetrofit(): Retrofit {
       return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<DogResponse> =
                getRetrofit().create(ApiService::class.java).getDogsByBreeds("$query/images")

            val puppies: DogResponse? = call.body()
            runOnUiThread {

                if (call.isSuccessful) {
                    val images: List<String> = puppies?.imagenes ?: emptyList()

                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }

            }
        }

    }

    private fun showError() {
        Toast.makeText(this, "Ha Ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}