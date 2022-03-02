package com.example.mygym.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mygym.NUMBER_OF_PAGER_ITEM
import com.example.mygym.R

class ViewPagerAdapter(
    private var titles: List<String>,
    private var images: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.logoPagerTextView)
        val itemImage: ImageView = itemView.findViewById(R.id.logoPagerImageView)

        init {
            itemImage.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(itemView.context,
                    "You clicked on item = #${position + 1}", Toast.LENGTH_SHORT).show()
                when (position) {
                    0 -> Navigation.findNavController(it).navigate(R.id.action_startFragment_to_powerliftingFragment)
                    1 -> Navigation.findNavController(it).navigate(R.id.action_startFragment_to_boxingFragment)
                    2 -> Navigation.findNavController(it).navigate(R.id.action_startFragment_to_fitnessFragment)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_pager_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount() = NUMBER_OF_PAGER_ITEM

}