package com.example.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.ItemMovieBinding

class MovieAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit // ფუნქცია, რომელიც HomeFragment-იდან მოდის
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.apply {
            movieTitle.text = movie.title
            movieOverview.text = movie.description

            Glide.with(holder.itemView.context)
                .load(movie.posterPath)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.binding.moviePoster)
        }

        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount() = movies.size
}