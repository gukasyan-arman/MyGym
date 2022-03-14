package com.example.mygym.screen.personalarea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.mygym.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth

class AuthFragment : Fragment() {

    lateinit var binding: FragmentAuthBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.acceptRules.setOnCheckedChangeListener { _, _ ->
            binding.buttonGetCode.isEnabled = true
        }

        binding.buttonGetCode.setOnClickListener {
            binding.buttonVerify.isVisible = true
            binding.otpEditText.isVisible = true
            binding.buttonGetCode.text = "Получить код повторно"

        }

    }

}