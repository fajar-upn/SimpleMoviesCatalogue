package com.example.submission

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission.Data.ResultsItem
import com.example.submission.Database.FavoriteDAO
import com.example.submission.Model.FavoriteModel
import com.example.submission.Repository.FavoriteRepository
import com.example.submission.ui.Favorite.FavoriteViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_movies.*

class DetailMovies : AppCompatActivity(), View.OnClickListener {

    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w342"

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)

        val ivPosterBackground:ImageView = findViewById(R.id.iv_poster_background_detail)
        val ivPoster:ImageView = findViewById(R.id.iv_poster_detail)
        val tvTitle:TextView = findViewById(R.id.tv_title_detail)
        val tvReleaseDate:TextView = findViewById(R.id.tv_release_contents_date_detail)
        val tvRating:TextView = findViewById(R.id.tv_rating_contents_detail)
        val tvLanguage:TextView = findViewById(R.id.tv_language_contents_detail)
        val overview:TextView = findViewById(R.id.tv_overview_detail)

        btn_favorite_detail.setOnClickListener(this)

        val movies:ResultsItem? = intent.getParcelableExtra(EXTRA_MOVIES)

        if(movies!=null){
            Picasso.get()
                .load(BASE_IMAGE_URL+movies.backdrop_path)
                .transform(BlurTransformation(this,10,2))
                .into(ivPosterBackground)
            Glide.with(this).load(BASE_IMAGE_URL+movies.poster_path).into(ivPoster)
            tvTitle.text = movies.title
            tvReleaseDate.text = movies.release_date
            tvRating.text = movies.vote_average.toString()

            var language = ""
            if(movies.original_language == "en"){
                language = "English"
            }
            tvLanguage.text = language
            overview.text = movies.overview
        }
        val viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_favorite_detail->{
                val movies:ResultsItem? = intent.getParcelableExtra(EXTRA_MOVIES)

                val viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
                Log.d("title","${movies?.title}")
                Log.d("id_movies","${movies?.id}")

                val favorite = FavoriteModel(
                    0,
                    Integer.parseInt(movies?.id.toString()),
                    "${movies?.title}",
                    "${movies?.original_language}",
                    "${movies?.overview}",
                    "${movies?.poster_path}",
                    "${movies?.backdrop_path}",
                    "${movies?.vote_average}",
                    "${movies?.release_date}"
                )

                viewModel.addFavorite(favorite)

                Toast.makeText(this,"Your choose movies: ${movies?.title} added to favorite",Toast.LENGTH_SHORT).show()

            }
        }
    }
}

