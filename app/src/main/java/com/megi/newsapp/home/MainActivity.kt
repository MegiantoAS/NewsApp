package com.megi.newsapp.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.megi.core.data.Resource
import com.megi.core.ui.NewsAdapter
import com.megi.newsapp.R
import com.megi.newsapp.databinding.ActivityMainBinding
import com.megi.newsapp.detail.DetailActivity
import com.megi.newsapp.settings.SettingActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showListNews()
    }

    private fun showListNews() {
        val adapterCat = NewsAdapter()
        adapterCat.onItemClick = { selected ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selected)
            startActivity(intent)
        }

        viewModel.news.observe(this){ news->
            if (news != null){
                when(news){
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapterCat.setList(news.data!!)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.vError.root.visibility = View.VISIBLE
                        binding.vError.tvError.text = news.message?: getString(R.string.errorr)
                    }
                }
            }
        }
        with(binding.rvNews){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterCat
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_favorite ->{
                val uri = Uri.parse("news://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
            R.id.nav_home ->{
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_settings ->{
                startActivity(Intent(this, SettingActivity::class.java))
            }
            else -> null
        }?: return super.onOptionsItemSelected(item)
        return true
    }
}
