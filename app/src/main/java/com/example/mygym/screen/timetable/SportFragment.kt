package com.example.mygym.screen.timetable

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.mygym.R
import com.example.mygym.databinding.FragmentSportBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SportFragment : Fragment() {

    lateinit var binding: FragmentSportBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = auth.currentUser
    private var databaseReference = FirebaseDatabase.getInstance().reference.child("users")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSportBinding.inflate(inflater, container, false)

        initBaseScreen()



        return binding.root
    }

    private fun initBaseScreen() {
        if (currentUser != null) {
            binding.enroll.isVisible = true
            binding.goToAuthBtn.isVisible = false
            binding.textView9.isVisible = false
        } else {
            binding.enroll.isVisible = false
            binding.goToAuthBtn.isVisible = true
            binding.textView9.isVisible = true
            binding.goToAuthBtn.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_sportFragment_to_authFragment)
            }
        }
    }

    private fun enroll() {
        currentUser
    }

}