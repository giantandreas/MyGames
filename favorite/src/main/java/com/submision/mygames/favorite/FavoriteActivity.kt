package com.submision.mygames.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.submision.mygames.favorite.databinding.ActivityFavoriteBinding
import com.submision.mygames.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)
        supportActionBar?.title = "Favorites"

        val mFragmentManager = supportFragmentManager
        val favoriteFragment = FavoriteFragment()
        val fragment = mFragmentManager.findFragmentByTag(FavoriteFragment::class.java.simpleName)
        if (fragment !is FavoriteFragment){
            mFragmentManager.commit {
                add(R.id.favorite_frame_container, favoriteFragment, FavoriteFragment::class.java.simpleName)
            }
        }
    }


}