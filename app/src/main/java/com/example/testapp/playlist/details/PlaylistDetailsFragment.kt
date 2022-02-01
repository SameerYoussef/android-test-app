package com.example.testapp.playlist.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.testapp.databinding.FragmentPlaylistDetailsBinding
import com.example.testapp.playlist.PlaylistApi
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    private lateinit var viewModel: PlaylistDetailsViewModel
    private var _binding: FragmentPlaylistDetailsBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

    private val args: PlaylistDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlaylistDetailsBinding.inflate(inflater, container, false)
        val id = args.playlistId

        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistDetailsViewModel::class.java]
        viewModel.getPlaylistDetails(id)

        viewModel.playlistDetails.observe(this as LifecycleOwner) {
            val playlist = it.getOrDefault(PlaylistDetails("","Sorry!","Playlist not found"))
            binding.playlistName.text = playlist.name
            binding.playlistDetails.text = playlist.details
        }
        return binding.root
    }
}
