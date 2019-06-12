package com.dguardado19.laboequipos.gui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dguardado19.laboequipos.R
import com.dguardado19.laboequipos.entities.Movie
import kotlinx.android.synthetic.main.main_content_fragment_layout.view.*

class MainContentFragment: Fragment() {

     var movie = Movie()

    companion object {
        fun newInstance(movie: Movie): MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.movie = movie
            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.main_content_fragment_layout, container, false)

        bindData(view)

        return view
    }

    fun bindData(view: View){
        view.genre_main_content_fragment.text = movie.Genre
        view.movie_title_main_content_fragment.text = movie.Title
        view.movie_rate_main_content_fragment.text = movie.Country
        view.plot_main_content_fragment.text = movie.Plot
        view.actors_main_content_fragment.text = movie.Actors
        view.movie_rate_main_content_fragment.text = movie.imdbRating
        view.director_main_content_fragment.text = movie.Director
        view.released_main_content_fragment.text = movie.Released
        view.runtime_main_content_fragment.text = movie.Runtime

        Glide.with(view).load(movie.Poster)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.image_main_content_fragment)
    }

}