package com.example.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.MovieAdapter
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadMovies()
    }

    private fun loadMovies() {
        val mockMovies = listOf(
            Movie(
                1, "Inception",
                "http://www.nolanfans.com/images/posters/inception/p4xfull.jpg",
                "Cobb steals information from his targets...", 8.8
            ),
            Movie(
                2, "Interstellar",
                "https://wallpapercave.com/wp/wp1817975.jpg",
                "A team of explorers travel through a wormhole...", 8.6
            ),
            Movie(
                3, "The Dark Knight",
                "https://www.themoviedb.org/t/p/original/pKKvCaL1TPTVtbI6EeliyND3api.jpg",
                "Batman raises the stakes in his war on crime...", 9.0
            )
        )

        binding.recyclerView.adapter = MovieAdapter(mockMovies) { movie ->
            val bundle = Bundle().apply {
                putString("title", movie.title)
                putString("desc", movie.description)
                putString("image", movie.posterPath)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundle
            )
        }
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


