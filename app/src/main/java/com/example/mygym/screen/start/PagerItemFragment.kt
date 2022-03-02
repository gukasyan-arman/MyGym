package com.example.mygym.screen.start

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygym.R
import com.example.mygym.databinding.FragmentPagerItemBinding
import androidx.annotation.NonNull
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2


class PagerItemFragment : Fragment() {

    private val LOG_TAG = "AndroidExample"
    private var counter = 0
    private lateinit var binding: FragmentPagerItemBinding
    private val items = listOf("Powerlifting", "Boxing", "Fitness")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

    }

}