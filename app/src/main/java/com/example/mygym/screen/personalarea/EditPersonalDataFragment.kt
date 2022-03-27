package com.example.mygym.screen.personalarea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygym.R
import com.example.mygym.databinding.FragmentEditPersonalDataBinding

class EditPersonalDataFragment : Fragment() {

    lateinit var binding: FragmentEditPersonalDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditPersonalDataBinding.inflate(inflater, container, false)
        return binding.root
    }

}