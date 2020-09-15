package com.example.submission.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission.Model.FavoriteModel
import com.example.submission.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation

class FavoriteAdapter (val context: Context): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){

    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    private var favoriteModel = emptyList<FavoriteModel>()
    private lateinit var detailFavoriteOnItemClickCallback: DetailFavoriteOnItemClickCallback

    fun setData(favorite:List<FavoriteModel>){
        this.favoriteModel = favorite
        notifyDataSetChanged()
    }

    fun setOnClickListener(detailFavoriteOnItemClickCallback: DetailFavoriteOnItemClickCallback){
        this.detailFavoriteOnItemClickCallback = detailFavoriteOnItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_card_movies,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        Glide.with(context)
            .load(BASE_IMAGE_URL+favoriteModel[position].poster_path)
            .apply(RequestOptions().override(350,550))
            .into(holder.ivPoster)

        Picasso.get()
            .load(BASE_IMAGE_URL+favoriteModel[position].backdrop_path)
            .transform(BlurTransformation(context,10,2))
            .into(holder.ivPosterBackground)

        holder.tvTitle.text = favoriteModel[position].title
        holder.tvRating.text = favoriteModel[position].vote_averege
        holder.tvRelease.text = favoriteModel[position].release_date

        var language = ""
        if(favoriteModel[position].language == "en"){
            language = "english"
        }

        if(favoriteModel[position].language == "id"){
            language = "indonesia"
        }

        holder.tvLanguage.text = language

        holder.itemView.setOnClickListener{
            detailFavoriteOnItemClickCallback.onItemClicked(favoriteModel[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        Log.d("favorite", favoriteModel.size.toString())
        return favoriteModel.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var ivPoster: ImageView = itemView.findViewById(R.id.iv_poster_card)
        var ivPosterBackground: ImageView = itemView.findViewById(R.id.iv_poster_card_background)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title_card)
        var tvRating: TextView = itemView.findViewById(R.id.tv_rating_card)
        var tvRelease: TextView = itemView.findViewById(R.id.tv_release_date_card)
        var tvLanguage: TextView = itemView.findViewById(R.id.tv_language_card)
    }

    interface DetailFavoriteOnItemClickCallback{
        fun onItemClicked(data:FavoriteModel)
    }

}