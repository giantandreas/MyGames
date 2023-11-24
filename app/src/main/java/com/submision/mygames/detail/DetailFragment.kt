package com.submision.mygames.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.submision.mygames.R
import com.submision.mygames.core.domain.model.Game
import com.submision.mygames.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val game = if (arguments != null){
            arguments?.getParcelable<Game>(SELECTED_GAME)
        } else {
            null
        }
        showDetailGame(game)
    }

    private fun showDetailGame(game: Game?){
        game?.let {
            binding.tvDetailName.text = game.name
            binding.tvDetailGenre.text = game.genre
            binding.tvDetailPubPlat.text= getString(R.string.platform_publisher, game.platform, game.publisher)
            binding.tvDetailDescription.text = game.description
            Glide.with(requireActivity())
                .load(game.imageUrl)
                .into(binding.ivDetailImage)

            var statusFavorite = game.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteGame(game, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_not_favorite))
        }
    }

    companion object {
        const val SELECTED_GAME = "selected_game"
    }
}