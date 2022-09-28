package com.desafio.filmes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.desafio.filmes.adapters.RecyclerViewAdapter
import com.desafio.filmes.databinding.ActivityMainBinding
import com.desafio.filmes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Permite o Data Binding observar o LiveData no lifecycle desta Activity
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.recyclerView.adapter = RecyclerViewAdapter()
    }
}