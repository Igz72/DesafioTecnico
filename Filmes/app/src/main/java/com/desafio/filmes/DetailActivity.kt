package com.desafio.filmes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.desafio.filmes.databinding.ActivityDetailBinding
import com.desafio.filmes.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    // Chave usada para enviar o id do filme
    companion object {
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Permite o Data Binding observar o LiveData no lifecycle desta Activity
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        // Acessa o valor do id recebido da MainActivity e atualiza os detalhes do filme
        val id = intent?.extras?.getInt(ID)
        viewModel.getMovieDetails(id!!)
    }
}