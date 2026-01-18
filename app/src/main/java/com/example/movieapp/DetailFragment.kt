package com.example.movieapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.example.movieapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var player: ExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        val title = arguments?.getString("title")
        val desc = arguments?.getString("desc")
        val image = arguments?.getString("image")

        binding.detailTitle.text = title
        binding.detailDescription.text = desc

        Glide.with(this)
            .load(image)
            .into(binding.detailPoster)

        binding.watchButton.setOnClickListener {
            playMovie("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        }
    }

    private fun playMovie(videoUrl: String) {
        binding.videoPlayer.visibility = View.VISIBLE
        binding.detailPoster.visibility = View.GONE

        player = ExoPlayer.Builder(requireContext()).build().also { exoPlayer ->
            binding.videoPlayer.player = exoPlayer
            val mediaItem = MediaItem.fromUri(videoUrl)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player?.release()
        player = null
        _binding = null
    }
}
