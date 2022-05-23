package com.example.mygym.screen.timetable

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
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
    private val sportViewModel: SportViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSportBinding.inflate(inflater, container, false)

        initBaseScreen()

        binding.enroll.setOnClickListener {
            enroll()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
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

        binding.room.text = sportViewModel.sportRoom.value
        binding.title.text = sportViewModel.sportTitle.value
        if (sportViewModel.sportDuration.value!! < 60) {
            binding.time.text = "Начало: ${sportViewModel.sportTime.value} (${sportViewModel.sportDuration.value} мин.)"
        } else {
            val hour = sportViewModel.sportDuration.value!! / 60
            val min = sportViewModel.sportDuration.value!! - (hour * 60)

            if (hour > 1) {
                binding.time.text = "Начало: ${sportViewModel.sportTime.value} ($hour часа $min мин.)"
            } else {
                binding.time.text = "Начало: ${sportViewModel.sportTime.value} ($hour час $min мин.)"
            }
        }
        binding.descriptionText.text = sportViewModel.sportDescription.value
        binding.trainerTv.text = "Тренер ${sportViewModel.sportTrainer.value}"
        val sportMembersCurrent = sportViewModel.sportMembersCurrent.value!!.toInt()
        val sportMembersMax = sportViewModel.sportMembersMax.value!!.toInt()
        if (sportMembersCurrent == sportMembersMax) {
            binding.enroll.text = "Свободных мест нет"
            binding.enroll.isEnabled = false
            binding.enroll.isClickable = false
        }
        binding.freePlaces.text = "Свободных мест: ${sportMembersMax - sportMembersCurrent} из $sportMembersMax"
    }

    private fun enroll() {

    }

}