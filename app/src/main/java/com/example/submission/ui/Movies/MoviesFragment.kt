package com.example.submission.ui.Movies


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission.Adapter.MoviesAdapter
import com.example.submission.Data.ResultsItem
import com.example.submission.DetailMovies
import com.example.submission.R
import com.example.submission.Repository.MoviesRepository

class MoviesFragment : Fragment() {

    private lateinit var progressBarLoading:ProgressBar
    private lateinit var  progressBarText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movies, container, false)

        /**
         * initialization loading bar
         */
        progressBarLoading = root.findViewById(R.id.pb_loading_movies)
        progressBarText = root.findViewById(R.id.tv_loading_movies)


        /**
         * initialization viewModel and viewModel.Factory
         */

//        val viewModel:MoviesViewModel = ViewModelProvider(this,ViewModelProvider
//            .NewInstanceFactory()).get(MoviesViewModel::class.java)
        val repository = MoviesRepository()
        val viewModelFactory = MoviesViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,viewModelFactory)
            .get(MoviesViewModel::class.java)


        /**
         * initalization adapter and recycler view
         */
        val adapter = context?.let { MoviesAdapter(it) }
        val rvMovies:RecyclerView = root.findViewById(R.id.rv_movies)

        progressBar(true)

        /**
         * set adapter to Linear Layout vertical
         */
        rvMovies.layoutManager = LinearLayoutManager(context)
        rvMovies.adapter = adapter

        adapter?.setOnClickCallback(object : MoviesAdapter.DetailOnItemClickcallback{
            override fun onItemClicked(data: ResultsItem) {
                val intent = Intent(requireContext(), DetailMovies::class.java)
                intent.putExtra(DetailMovies.EXTRA_MOVIES,data)
                startActivity(intent)
            }
        })

        /**
         * set movies data and get data with live data from viewModel
         */
//        viewModel.setMoviesData()
//        viewModel.getMoviesData().observe(viewLifecycleOwner,Observer<ArrayList<ResultsItem>>{
//            adapter?.setMoviesArrayList(it)
//            progressBar(false)
//        })
        viewModel.getMoviesData().observe(viewLifecycleOwner,Observer<ArrayList<ResultsItem>>{
            adapter?.setMoviesArrayList(it)
            progressBar(false)
        })


        /**
         * this fun to inflate menu
         */
        setHasOptionsMenu(true)

        return root
    }

    /**
     * this fun use to inflate search menu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
    }

    /**
     * this fun use search menu with query based title movies
     * in this fun query will be sent to view model and call data from api
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.search){

            /**
             * initialization viewModel and viewModel.Factory
             */
//            val viewModel:MoviesViewModel = ViewModelProvider(this,ViewModelProvider
//                .NewInstanceFactory()).get(MoviesViewModel::class.java)
            val repository = MoviesRepository()
            val viewModelFactory = MoviesViewModelFactory(repository)
            val viewModel = ViewModelProvider(this,viewModelFactory)
                .get(MoviesViewModel::class.java)

            /**
             * initialization adapter and recycler view
             */
            val adapter = context?.let { MoviesAdapter(it) }
            val rvMovies: RecyclerView? = activity?.findViewById(R.id.rv_movies)
            rvMovies?.layoutManager = LinearLayoutManager(context)
            rvMovies?.adapter = adapter
            progressBar(true)

//            viewModel.setMoviesData()
//            viewModel.getMoviesData().observe(viewLifecycleOwner,Observer<ArrayList<ResultsItem>>{
//                adapter?.setMoviesArrayList(it)
//                progressBar(false)
//            })
            viewModel.getMoviesData().observe(viewLifecycleOwner,Observer<ArrayList<ResultsItem>>{
                adapter?.setMoviesArrayList(it)
                progressBar(false)
            })

            val searchView: SearchView? = item.actionView as? SearchView

            /**
             * if user submit will search title movies from api
             */
            searchView?.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(requireContext(),"Search movies: $query",Toast.LENGTH_SHORT).show()
                    progressBar(true)
                    if (query != null) {
                        if(query.length > 3){
//                            viewModel.setSearchMoviesData("$query")
                            viewModel.getSearchMoviesData("$query").observe(viewLifecycleOwner,Observer<ArrayList<ResultsItem>>{
                                adapter?.setMoviesArrayList(it)
                                progressBar(false)
                            })
                        }else{
                            Toast.makeText(requireContext(),"Please search more than 4 characters...",Toast.LENGTH_SHORT).show()
                        }
                    }

                    adapter?.setOnClickCallback(object : MoviesAdapter.DetailOnItemClickcallback{
                        override fun onItemClicked(data: ResultsItem) {
                            val intent = Intent(requireContext(), DetailMovies::class.java)
                            intent.putExtra(DetailMovies.EXTRA_MOVIES,data)
                            startActivity(intent)
                        }
                    })
                    searchView.clearFocus()
                    return true
                }

                /**
                 * if user type word will live search movies data from api
                 */
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        if(newText.length > 3) {
//                            viewModel.setSearchMoviesData("$newText")
                            viewModel.getSearchMoviesData("$newText")
                                .observe(viewLifecycleOwner, Observer<ArrayList<ResultsItem>> {
                                    adapter?.setMoviesArrayList(it)
                                    progressBar(false)
                                })
                        }
                    }

                    /**
                     * this fun use to carry data from recycler view to detail activity
                     */
                    adapter?.setOnClickCallback(object : MoviesAdapter.DetailOnItemClickcallback{
                        override fun onItemClicked(data: ResultsItem) {
                            val intent = Intent(requireContext(), DetailMovies::class.java)
                            intent.putExtra(DetailMovies.EXTRA_MOVIES,data)
                            startActivity(intent)
                        }
                    })
                    return true
                }
            })
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * this fun to set visibility loading bar
     */
    private fun progressBar(show:Boolean){
        if(show){
            progressBarLoading.visibility = View.VISIBLE
            progressBarText.visibility = View.VISIBLE
        }else{
            progressBarLoading.visibility = View.INVISIBLE
            progressBarText.visibility = View.INVISIBLE
        }
    }
}
