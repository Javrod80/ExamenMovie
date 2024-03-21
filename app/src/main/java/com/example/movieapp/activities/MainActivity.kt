package com.example.movieapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.activities.adapter.MovieAdapter
import com.example.movieapp.activities.data.MovieServiceApi
import com.example.movieapp.activities.data.Movies
import com.example.movieapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private var moviesList: List<Movies> = listOf()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.searchView.setOnQueryTextListener(this)


        initRecyledView()



    }
    private fun initRecyledView() {
        adapter = MovieAdapter(moviesList) {
            onItemClickListener(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


    }
    private fun onItemClickListener(position: Int) {
        val movies: Movies = moviesList[position]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("MOVIES_ID", movies.id)

        startActivity(intent)

    }
    private fun searchMovies(query: String) {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call: MovieServiceApi = retrofit.create(MovieServiceApi::class.java)



        CoroutineScope(Dispatchers.IO).launch {
            val response = call.searchMovies(query)

            runOnUiThread {
                if (response.body() != null) {
                    Log.i("HTTP", "Respuesta correcta")
                    moviesList = response.body()?.movies.orEmpty()
                    adapter.updateItems(moviesList)



                } else {
                    Log.i("HTTP", "Respuesta incorrecta")
                    showError()


                }
                hideKeyboard()
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean { // listener
        if (!query.isNullOrEmpty()) {
            searchMovies(query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
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






}