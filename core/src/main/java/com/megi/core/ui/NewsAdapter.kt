package com.megi.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.megi.core.R
import com.megi.core.databinding.ItemListNewsBinding
import com.megi.core.domain.model.NewsModel

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ListViewHolder>(){

    private var dataList = ArrayList<NewsModel>()
    var onItemClick: ((NewsModel) -> Unit)? = null

    fun setList(newListData : List<NewsModel>){
        if (newListData == null) return
        dataList.clear()
        dataList.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_news, parent, false))


    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listData = dataList[position]
        holder.bind(listData)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemListNewsBinding.bind(itemView)
        fun bind(data: NewsModel){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.publishAt
            }
        }
        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(dataList[adapterPosition])
            }
        }
    }
}