package com.megi.newsapp.favorite

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.megi.core.ui.NewsAdapter
import com.megi.newsapp.detail.DetailActivity
import com.megi.newsapp.favorite.databinding.ActivityFavoriteBinding
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(com.megi.newsapp.R.string.favorite_title)

        loadKoinModules(favoriteModule)
        dataFavoriteNews()
    }

    private fun dataFavoriteNews() {
        val adapterFav = NewsAdapter()
        adapterFav.onItemClick = { selectData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectData)
            startActivity(intent)
        }

        viewModel.favoriteNews.observe(this){
            adapterFav.setList(it)
            binding.emptyFav.visibility = if ( it.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvNews){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterFav
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}