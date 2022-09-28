package com.desafio.filmes.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.desafio.filmes.DetailActivity
import com.desafio.filmes.databinding.ViewItemBinding
import com.desafio.filmes.model.Movie

class RecyclerViewAdapter : ListAdapter<Movie, RecyclerViewAdapter.ImageViewHolder>(DiffCallback) {

    class ImageViewHolder(private var binding: ViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie

            // Importante para forçar o data binding imediatamente
            binding.executePendingBindings()
        }
    }

    // Permite ao RecyclerView verificar quais itens foram modificados
    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.poster_url == newItem.poster_url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)

        // Ao clicar no item, navega para a DetailActivity enviando o id do filme selecionado
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ID, movie.id)
            context.startActivity(intent)
        }
    }
}