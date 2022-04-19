package com.example.mygym.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygym.R
import com.example.mygym.databinding.CoachItemBinding
import com.example.mygym.model.Coach

class CoachAdapter(private val coachList: ArrayList<Coach>): RecyclerView.Adapter<CoachAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.coach_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = coachList[position]
        with(holder) {
            binding.coachFio.text = currentItem.fio
            binding.coachPost.text = currentItem.post
        }
    }

    override fun getItemCount(): Int {
        return coachList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CoachItemBinding.bind(itemView)
    }
}