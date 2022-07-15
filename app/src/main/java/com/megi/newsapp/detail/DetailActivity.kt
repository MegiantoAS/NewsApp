package com.megi.newsapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.megi.core.domain.model.NewsModel
import com.megi.newsapp.R
import com.megi.newsapp.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val intentDetail = intent.getParcelableExtra<NewsModel>(EXTRA_DATA)
        showDetailNews(intentDetail)
    }

    private fun showDetailNews(intentDetail: NewsModel?) {
        intentDetail?.let {
            supportActionBar?.title = intentDetail.name
            binding.content.tvDetailName.text = intentDetail.title
            binding.content.tvDetail.text = intentDetail.description
            binding.content.tvDetailPublish.text = intentDetail.publishAt
            binding.content.tvDetailNameAuth.text = intentDetail.name
            Glide.with(this)
                .load(intentDetail.image)
                .into(binding.ivDetailImage)

            var btnFav = intentDetail.favoriteNews
            setState(btnFav)
            binding.fab.setOnClickListener {
                btnFav = !btnFav
                viewModel.setFavNews(intentDetail, btnFav)
                setState(btnFav)
            }
        }
    }

    private fun setState(btnFav: Boolean) {
        if (btnFav){
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        }else
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}