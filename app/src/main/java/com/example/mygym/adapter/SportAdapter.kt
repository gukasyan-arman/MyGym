package com.example.mygym.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygym.R
import com.example.mygym.databinding.SportItemBinding
import com.example.mygym.model.Sport

class SportAdapter(private val sportList: ArrayList<Sport>): RecyclerView.Adapter<SportAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class MyViewHolder(itemView: View, listener: SportAdapter.onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val binding = SportItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.sport_item, parent, false)
        return SportAdapter.MyViewHolder(itemView, mListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = sportList[position]
        with(holder) {
            binding.timeTv.text = currentItem.time
            binding.titleTv.text = currentItem.title
            binding.trainerTv.text = currentItem.trainer
            binding.roomTv.text = currentItem.room
            binding.membersCurrentTv.text = currentItem.membersCurrent.toString()
            binding.membersMaxTv.text = currentItem.membersMax.toString()

            if (currentItem.duration < 60) {
                binding.durationTv.text = currentItem.duration.toString() + " мин."
            } else {
                val hour = currentItem.duration / 60
                val min = currentItem.duration - (hour * 60)

                if (hour > 1) {
                    binding.durationTv.text = "$hour часа $min мин."
                } else {
                    binding.durationTv.text = "$hour час $min мин."
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return sportList.size
    }
}