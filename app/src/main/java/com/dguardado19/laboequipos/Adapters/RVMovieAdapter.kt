package com.dguardado19.laboequipos.gui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dguardado19.laboequipos.R
import com.dguardado19.laboequipos.entities.Movie
import kotlinx.android.synthetic.main.cardview_movie.view.*

class RVMovieAdapter(var movies: List<Movie>, val clickListener: (Movie) -> Unit) : RecyclerView.Adapter<RVMovieAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position], clickListener)

    fun changeDataSet(newMovieList: List<Movie>) {
        movies = newMovieList
        notifyDataSetChanged()
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        fun bind(movie: Movie, clickListener: (Movie) -> Unit) = with(itemView){
            Glide.with(itemView.context)
                .load(movie.Poster)
                .placeholder(R.drawable.ic_launcher_background)
                .into(movie_image_cv)
            movie_title_cv.text = movie.Title
            //movie_plot_cv.text = movie.Plot
            movie_rate_cv.text = movie.Country
            //movie_runtime_cv.text = movie.Runtime
            this.setOnClickListener { clickListener(movie) }
        }
    }
}