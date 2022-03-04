package com.example.mygym.screen.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygym.databinding.FragmentPagerItemBinding

class PagerItemFragment : Fragment() {

    private lateinit var binding: FragmentPagerItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagerItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}