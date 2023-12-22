package com.example.parentschooler.ui.parents.homepage

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parentschooler.data.news.PostsItem
import com.example.parentschooler.databinding.ItemNewsBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


fun String?.formatNewsDate(): String {
    return this?.let {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        try {
            val date = inputFormat.parse(it)
            return outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        ""
    } ?: ""
}

class NewsAdapter(private val itemNews: List<PostsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: PostsItem) {
            binding.tvNewsTitle.text = news.title?.trim()
            binding.tvNewsDate.text = news.pusblisedAt?.formatNewsDate()
            Glide.with(binding.ivNewsItem).load(news.image).into(binding.ivNewsItem)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, WebviewActivity::class.java)
                intent.putExtra("NEWS_LINK", news.link)
                itemView.context.startActivity(intent)
            }
            binding.icBrowseNews.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(news.link)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = itemNews.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(itemNews[position])
    }
}
