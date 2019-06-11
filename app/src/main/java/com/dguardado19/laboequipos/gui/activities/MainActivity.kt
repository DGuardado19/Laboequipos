package com.dguardado19.laboequipos.gui.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.dguardado19.laboequipos.R
import com.dguardado19.laboequipos.entities.Movie
import com.dguardado19.laboequipos.gui.fragments.MainContentFragment
import com.dguardado19.laboequipos.gui.fragments.MainListFragment
import com.dguardado19.laboequipos.gui.utils.AppConstants
import com.dguardado19.laboequipos.viewModel.peliculasViewModel
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), MainListFragment.SearchNewMovieListener {
    private lateinit var mainFragment : MainListFragment
    private lateinit var mainContentFragment: MainContentFragment
    private lateinit var peliculasViewModel: peliculasViewModel

    private var movieList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        peliculasViewModel = ViewModelProviders.of(this).get(com.dguardado19.laboequipos.viewModel.peliculasViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieList = savedInstanceState?.getParcelableArrayList(AppConstants.dataset_saveinstance_key) ?: ArrayList()

        initMainFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.dataset_saveinstance_key, movieList)
        super.onSaveInstanceState(outState)
    }

    fun initMainFragment(){
        mainFragment = MainListFragment.newInstance(movieList)

        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment
        else {
            mainContentFragment = MainContentFragment.newInstance(Movie("N/A","N/A","N/A","N/A","N/A","N/A","N/A","N/A"))
            changeFragment(R.id.land_main_cont_fragment, mainContentFragment)

            R.id.land_main_fragment
        }

        changeFragment(resource, mainFragment)
    }

    fun addMovieToList(movie: Movie) {
        movieList.add(movie)
        mainFragment.updateMoviesAdapter(movieList)
        Log.d("Number", movieList.size.toString())
    }

    fun changeMovieList(allMovies: LiveData<List<Movie>>) {
        movieList = allMovies.value as ArrayList<Movie>
        mainFragment.updateMoviesAdapter(movieList)
    }

    override fun searchMovie(movieName: String) {
        changeMovieList(peliculasViewModel.getAllMovies())
       // addMovieToList(peliculasViewModel.getAllMovies())
       // FetchMovie().execute(movieName)
    }

    override fun managePortraitItemClick(movie: Movie) {
        val movieBundle = Bundle()
        movieBundle.putParcelable("MOVIE", movie)
        startActivity(Intent(this, MovieViewerActivity::class.java).putExtras(movieBundle))
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun manageLandscapeItemClick(movie: Movie) {
        mainContentFragment = MainContentFragment.newInstance(movie)
        changeFragment(R.id.land_main_cont_fragment, mainContentFragment)
    }


}

