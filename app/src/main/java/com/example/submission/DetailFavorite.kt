package com.example.submission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission.Data.ResultsItem
import com.example.submission.Model.FavoriteModel
import com.example.submission.ui.Favorite.FavoriteFragment
import com.example.submission.ui.Favorite.FavoriteViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_favorite.*

class DetailFavorite : AppCompatActivity(), View.OnClickListener {
    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w342"

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite)

        val ivPosterBackground: ImageView = findViewById(R.id.iv_poster_background_detail_favorite)
        val ivPoster: ImageView = findViewById(R.id.iv_poster_detail_favorite)
        val tvTitle: TextView = findViewById(R.id.tv_title_detail_favorite)
        val tvReleaseDate: TextView = findViewById(R.id.tv_release_contents_date_detail_favorite)
        val tvRating: TextView = findViewById(R.id.tv_rating_contents_detail_favorite)
        val tvLanguage: TextView = findViewById(R.id.tv_language_contents_detail_favorite)
        val overview: TextView = findViewById(R.id.tv_overview_detail_favorite)

        btn_favorite_detail_favorite.setOnClickListener(this)

        val movies: FavoriteModel = intent.getParcelableExtra(EXTRA_MOVIES)

        if(movies!=null){
            Picasso.get()
                .load(BASE_IMAGE_URL+movies.backdrop_path)
                .transform(BlurTransformation(this,10,2))
                .into(ivPosterBackground)
            Glide.with(this).load(BASE_IMAGE_URL+movies.poster_path).into(ivPoster)
            tvTitle.text = movies.title
            tvReleaseDate.text = movies.release_date
            tvRating.text = movies.vote_averege

            var language = ""
            if(movies.language == "en"){
                language = "English"
            }
            tvLanguage.text = language
            overview.text = movies.overview
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_favorite_detail_favorite->{
                val movies: FavoriteModel = intent.getParcelableExtra(EXTRA_MOVIES)

                val viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

                viewModel.deleteFavorite(movies)
                Toast.makeText(this,"Successfull delete ${movies.title} from favorite", Toast.LENGTH_SHORT).show()

            }
        }
    }
}