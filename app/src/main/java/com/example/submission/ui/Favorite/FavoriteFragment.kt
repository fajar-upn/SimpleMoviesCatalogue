package com.example.submission.ui.Favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission.Adapter.FavoriteAdapter
import com.example.submission.DetailFavorite
import com.example.submission.Model.FavoriteModel
import com.example.submission.R

class FavoriteFragment : Fragment() {

    private lateinit var progressLoadingBar:ProgressBar
    private lateinit var progressBarText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_favorite, container, false)

        /**
         * initialization loading bar
         */
        progressLoadingBar = root.findViewById(R.id.pb_loading_movies_favorite)
        progressBarText = root.findViewById(R.id.tv_loading_movies_favorite)

        /**
         * initialization viewModel
         */
        val viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        /**
         * initalization adapter and recycler view
         */
        val adapter = context?.let { FavoriteAdapter(it) }
        val rvFavorite:RecyclerView = root.findViewById(R.id.rv_movies_favorite)

        progressBar(true)

        /**
         * set adapter to Linear Layout vertical
         */
        rvFavorite.layoutManager = LinearLayoutManager(context)
        rvFavorite.adapter = adapter

        /**
         * set favorite movies data and get data with live data from viewModel
         */
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { favorite->
            adapter?.setData(favorite)
            progressBar(false)
        })

        adapter?.setOnClickListener(object: FavoriteAdapter.DetailFavoriteOnItemClickCallback{
            override fun onItemClicked(data: FavoriteModel) {
                val intent = Intent(requireContext(),DetailFavorite::class.java)
                intent.putExtra(DetailFavorite.EXTRA_MOVIES,data)
                startActivity(intent)
            }

        })

        return root
    }

    private fun progressBar(show:Boolean){
        if(show){
            progressLoadingBar.visibility = View.VISIBLE
            progressBarText.visibility = View.VISIBLE
        }else{
            progressLoadingBar.visibility = View.INVISIBLE
            progressBarText.visibility = View.INVISIBLE
        }
    }
}

