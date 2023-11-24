package com.submision.mygames.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.submision.mygames.core.ui.GameAdapter
import com.submision.mygames.detail.DetailFragment
import com.submision.mygames.favorite.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteGames()
    }

    private fun getFavoriteGames() {
        val gameAdapter = GameAdapter()

        gameAdapter.onItemClick = {selectedGame ->
            val detailFragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(DetailFragment.SELECTED_GAME, selectedGame)
            detailFragment.arguments = bundle
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.favorite_frame_container, detailFragment, DetailFragment::class.java.simpleName)
            }
        }

        favoriteViewModel.favoriteGames.observe(this){
            gameAdapter.setData(it)
        }

        binding.rvGames.layoutManager = LinearLayoutManager(context)
        binding.rvGames.adapter = gameAdapter
    }


}