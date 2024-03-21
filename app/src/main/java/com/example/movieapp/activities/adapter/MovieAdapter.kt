package com.example.movieapp.activities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.activities.data.Movies
import com.example.movieapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter (private var items: List<Movies> = listOf(), val onClickListener :(position: Int) -> Unit):
    RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMovieBinding.inflate((LayoutInflater.from(parent.context)),parent,false)
        return MoviesViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
    }


    fun updateItems(results: List<Movies>?) {
        items = results!!
        notifyDataSetChanged()
    }






    override fun getItemCount(): Int {
        return items.size
    }




    class MoviesViewHolder(val binding: ItemMovieBinding):
        RecyclerView.ViewHolder(binding.root){

        fun render (movies: Movies){

            binding.textViewMovies.text = movies.title
            binding.textViewYear.text = movies.year
           Picasso.get().load(movies.poster).into(binding.movieImageView)
        }

    }





}
