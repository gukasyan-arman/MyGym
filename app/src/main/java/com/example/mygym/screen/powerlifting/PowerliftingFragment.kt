package com.example.mygym.screen.powerlifting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygym.R
import com.example.mygym.databinding.FragmentPowerliftingBinding

class PowerliftingFragment : Fragment() {

    lateinit var binding: FragmentPowerliftingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPowerliftingBinding.inflate(inflater, container, false)
        return binding.root
    }

}