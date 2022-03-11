package com.example.mygym.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygym.R

class ViewPagerImageAdapter(
    private var images: List<Int>): RecyclerView.Adapter<ViewPagerImageAdapter.PagerImageViewHolder>() {

    inner class PagerImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.photoItemPagerView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerImageAdapter.PagerImageViewHolder {
        return PagerImageViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.gym_photos_pager_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerImageAdapter.PagerImageViewHolder, position: Int) {
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount() = images.size
}