package com.example.movieapp.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.activities.data.MovieServiceApi
import com.example.movieapp.activities.data.Movies
import com.example.movieapp.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var movies: Movies
    private var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getStringExtra("MOVIES_ID")


        detailMovies(movieId!!)



    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {

        Picasso.get().load(movies.poster).into(binding.movieImageView)
        binding.plot.text = "Plot : ${movies.plot}"
        binding.director.text = "Director : ${movies.director}"
        binding.runtime.text = "Runtime : ${movies.runtime}"
        binding.genre.text = "Genre : ${movies.genre}"
        binding.movieName.text =  movies.title
        binding.country.text = "Country : ${movies.country}"
        binding.textViewYear.text = "Year : ${movies.year}"
        binding.awards.text = "Awards : ${movies.awards}"
        binding.votes.text = "Votes : ${movies.votes}"
        binding.released.text = "Released : ${movies.released}"
        binding.rating.text = "Rating :  ${movies.rating}"



    }


    private fun detailMovies (id: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call: MovieServiceApi = retrofit.create(MovieServiceApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = call.moviesId(id)

            runOnUiThread {
                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta")
                    movies = response.body()!!
                    loadData()
                } else {
                    Log.i("HTTP", "respuesta incorrecta")
                }
                hideKeyboard()



            }


        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //barra
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }


        }
        return super.onOptionsItemSelected(item)


    }
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }


}
