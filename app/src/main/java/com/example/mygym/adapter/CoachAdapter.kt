package com.example.mygym.adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygym.R
import com.example.mygym.databinding.CoachItemBinding
import com.example.mygym.model.Coach
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class CoachAdapter(private val coachList: ArrayList<Coach>): RecyclerView.Adapter<CoachAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.coach_item, parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = coachList[position]
        with(holder) {
            binding.coachFio.text = currentItem.fullName()
            binding.coachPost.text = currentItem.post
            val storageReference = FirebaseStorage.getInstance().getReference("photo_of_coaches/trainerAvatar$adapterPosition.jpeg")
            Log.d("avatar", "photo_of_coaches/trainerAvatar$adapterPosition.jpeg")

            val localFile: File = File.createTempFile("coachAvatar", ".jpeg")
            storageReference.getFile(localFile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                binding.coachAvatar.setImageBitmap(bitmap)
            }
        }
    }

    override fun getItemCount(): Int {
        return coachList.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val binding = CoachItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }
        }
    }
}