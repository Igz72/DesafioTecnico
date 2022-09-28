package com.desafio.filmes.adapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.desafio.filmes.R
import com.desafio.filmes.model.*

// Usa o Coil para carregar a imagem de uma URL na ImageView
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()

        imageView.load(imageUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

// Atualiza as imagens do RecyclerView
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as RecyclerViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("textInt")
fun bindIntToText(textView: TextView, data: Int?) {
    textView.text = data.toString()
}

@BindingAdapter("textDouble")
fun bindDoubleToText(textView: TextView, data: Double?) {
    textView.text = data.toString()
}

// Atualiza o TextView com uma lista delimitada por vírgulas
@BindingAdapter("textList")
fun bindListToText(textView: TextView, data: List<String>?) {
    if (data != null) {
        textView.text = android.text.TextUtils.join(", ", data)
    }
}

// Atualiza o TextView com os nomes dos ProductionCountries
@BindingAdapter("textProductionCountries")
fun bindProductionCountriesToText(textView: TextView, data: List<ProductionCountry>?) {
    if (data != null) {
        val countries = mutableListOf<String>()
        for (country in data){
            countries.add(country.name)
        }
        textView.text = android.text.TextUtils.join(", ", countries)
    }
}

// Atualiza o TextView com os nomes das SpokenLanguages
@BindingAdapter("textLanguage")
fun bindLanguageToText(textView: TextView, data: List<SpokenLanguage>?) {
    if (data != null) {
        val languages = mutableListOf<String>()
        for (language in data){
            languages.add(language.name)
        }
        textView.text = android.text.TextUtils.join(", ", languages)
    }
}

// Altera a visibilidade da ProgressBar de acordo com o status da requisição
@BindingAdapter("ProgressBarStatus")
fun bindStatusProgressBar(progressBar: ProgressBar, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        else -> {
            progressBar.visibility = View.GONE
        }
    }
}

// Altera a visibilidade da imagem de erro de acordo com o status da requisição
@BindingAdapter("ErrorImageStatus")
fun bindStatusErrorImage(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
        }
        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}

// Altera a visibilidade da TextView de acordo com o status da requisição
@BindingAdapter("textStatus")
fun bindTextStatus(textView: TextView, status: ApiStatus?) {
    when (status) {
        ApiStatus.DONE -> {
            textView.visibility = View.VISIBLE
        }
        else -> {
            textView.visibility = View.GONE
        }
    }
}
