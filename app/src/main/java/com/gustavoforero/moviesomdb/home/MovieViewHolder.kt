package com.gustavoforero.moviesomdb.home

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), MovieItemView {
    override fun setType(type: String) {
        itemView.lab_type.text = type
    }

    override fun setTitle(title: String) {
        itemView.lab_movie_title.text = title

    }

    override fun setYear(year: String) {
        itemView.lab_movie_year.text = year
    }

    override fun loadPoster(url: String) {
        Picasso.get().load(url).into(itemView.img_poster)

    }
}
