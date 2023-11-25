package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(private val newsList: ArrayList<Article>,
                  private val listener: OnItemClickListener
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(article: Article)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val author: TextView = itemView.findViewById(R.id.author)
        val publishedAt: TextView = itemView.findViewById(R.id.publishedAt)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsList[position]

        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        holder.publishedAt.text = currentItem.publishedAt

        Picasso.get().load(currentItem.urlToImage).into(holder.image)

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem) // Notify the click event
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
