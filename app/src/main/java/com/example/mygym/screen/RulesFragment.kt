package com.example.mygym.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mygym.R
import com.example.mygym.databinding.FragmentRulesBinding
import com.example.mygym.showActionBar

class RulesFragment : Fragment() {

    lateinit var binding: FragmentRulesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showActionBar(requireActivity() as AppCompatActivity)
        binding = FragmentRulesBinding.inflate(inflater, container, false)
        return binding.root
    }

}