package com.example.submission.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission.Data.ResultsItem
import com.example.submission.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation

class MoviesAdapter(val context: Context): RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){

    
    private val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    private val result = ArrayList<ResultsItem>()
    private lateinit var detailOnItemClickcallback: DetailOnItemClickcallback

    fun setOnClickCallback(detailOnItemClickcallback: DetailOnItemClickcallback){
        this.detailOnItemClickcallback = detailOnItemClickcallback
    }

    fun setMoviesArrayList(items:ArrayList<ResultsItem>) {
        if(result.size>0){
            result.clear()
        }
        result.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_card_movies,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {

        Glide.with(context)
            .load(BASE_IMAGE_URL+result[position].poster_path)
            .apply(RequestOptions().override(350,550))
            .into(holder.ivPoster)

        Picasso.get()
            .load(BASE_IMAGE_URL+result[position].backdrop_path)
            .transform(BlurTransformation(context,10,2))
            .into(holder.ivPosterBackground)

//        holder.itemView.setBackgroundColor(backgroudColor[position%3])
        holder.tvTitle.text = result[position].title
        holder.tvRating.text = result[position].vote_average.toString()
        holder.tvRelease.text = result[position].release_date.toString()

        var language = ""
        if(result[position].original_language == "en"){
            language = "english"
        }

        if(result[position].original_language == "id"){
            language = "indonesia"
        }

        holder.tvLanguage.text = language

        holder.itemView.setOnClickListener{
            detailOnItemClickcallback.onItemClicked(result[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return result.size
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var ivPoster: ImageView = itemView.findViewById(R.id.iv_poster_card)
        var ivPosterBackground: ImageView = itemView.findViewById(R.id.iv_poster_card_background)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title_card)
        var tvRating:TextView = itemView.findViewById(R.id.tv_rating_card)
        var tvRelease:TextView = itemView.findViewById(R.id.tv_release_date_card)
        var tvLanguage:TextView = itemView.findViewById(R.id.tv_language_card)
    }

    interface DetailOnItemClickcallback{
        fun onItemClicked(data: ResultsItem)
    }

}