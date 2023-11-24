package com.submision.mygames.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.submision.mygames.R
import com.submision.mygames.core.data.Resource
import com.submision.mygames.core.ui.GameAdapter
import com.submision.mygames.databinding.FragmentHomeBinding
import com.submision.mygames.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), MenuProvider {

    private val homeViewModel: HomeViewModel by viewModel()

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

        if (activity != null){
            val gameAdapter = GameAdapter()
            gameAdapter.onItemClick = {selectedGame ->
                val detailFragment = DetailFragment()
                val bundle = Bundle()
                bundle.putParcelable(DetailFragment.SELECTED_GAME, selectedGame)
                detailFragment.arguments = bundle
                parentFragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.frame_container, detailFragment, DetailFragment::class.java.simpleName)
                }
            }

            homeViewModel.games.observe(viewLifecycleOwner) {games ->
                when(games){
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = games.message ?: getString(R.string.something_wrong)
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        gameAdapter.setData(games.data)
                    }
                }
            }

            with(binding.rvGames){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
        activity?.addMenuProvider(this, viewLifecycleOwner)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.favorite) {
            val uri = Uri.parse("mygames://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return true
    }
}