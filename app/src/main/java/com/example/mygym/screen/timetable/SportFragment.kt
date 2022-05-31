package com.example.mygym.screen.timetable

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mygym.R
import com.example.mygym.databinding.FragmentSportBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.properties.Delegates


class SportFragment : Fragment() {

    lateinit var binding: FragmentSportBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = auth.currentUser
    private var databaseReference = FirebaseDatabase.getInstance().reference
    private var sportReference: DatabaseReference = databaseReference.child("lessons")
    private var userReference: DatabaseReference = databaseReference
        .child("users").child(currentUser!!.phoneNumber.toString())
    private val sportViewModel: SportViewModel by activityViewModels()
    private var sportMembersCurrent by Delegates.notNull<Long>()
    private var membersCurrent by Delegates.notNull<Long>()
    private var sportMembersMax by Delegates.notNull<Long>()


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSportBinding.inflate(inflater, container, false)

        sportReference.child(sportViewModel.date.value.toString()).child(sportViewModel.sportId.value.toString())
            .child("membersCurrent").get().addOnSuccessListener {
                membersCurrent = it.value as Long
            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }

        membersCurrent = sportViewModel.sportMembersCurrent.value!!
        sportMembersMax = sportViewModel.sportMembersMax.value!!

        initBaseScreen()
        sportViewModel.sportMembersCurrent.observe(this, {
            binding.freePlaces.text = "Свободных мест: ${sportMembersMax - sportMembersCurrent} из $sportMembersMax"
        })

        binding.enroll.setOnClickListener {
            enroll()
        }

        binding.unenroll.setOnClickListener {
            unenroll()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initBaseScreen() {

        if (currentUser != null) {
            binding.goToAuthBtn.isVisible = false
            binding.textView9.isVisible = false
            userReference.child(sportViewModel.date.value.toString()).child(sportViewModel.sportId.value.toString()).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    binding.enroll.isVisible = false
                    binding.unenroll.isVisible = true
                } else {
                    binding.enroll.isVisible = true
                    binding.unenroll.isVisible = false
                }
            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }


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
        sportMembersCurrent = sportViewModel.sportMembersCurrent.value!!
        sportMembersMax = sportViewModel.sportMembersMax.value!!
        if (sportMembersCurrent == sportMembersMax) {
            binding.enroll.text = "Свободных мест нет"
            binding.enroll.isEnabled = false
            binding.enroll.isClickable = false
        }
        binding.freePlaces.text = "Свободных мест: ${sportMembersMax - sportMembersCurrent} из $sportMembersMax"
    }

    private fun enroll() {

        binding.enroll.isVisible = false
        binding.unenroll.isVisible = true

        val currentSportReference = userReference.child(sportViewModel.date.value.toString()).child(sportViewModel.sportId.value.toString())

        currentSportReference.child("title").setValue(sportViewModel.sportTitle.value)
        currentSportReference.child("description").setValue(sportViewModel.sportDescription.value)
        currentSportReference.child("time").setValue(sportViewModel.sportTime.value)
        currentSportReference.child("duration").setValue(sportViewModel.sportDuration.value)
        currentSportReference.child("room").setValue(sportViewModel.sportRoom.value)
        currentSportReference.child("trainer").setValue(sportViewModel.sportTrainer.value)

        membersCurrent++

        sportReference.child(sportViewModel.date.value.toString()).child(sportViewModel.sportId.value.toString())
        .child("membersCurrent").setValue(membersCurrent)
        sportViewModel.sportMembersCurrent.value = membersCurrent
        Toast.makeText(
            requireContext(),
            "Вы записаны на занятие ${sportViewModel.sportTitle.value} в ${sportViewModel.sportTime.value}!",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun unenroll() {

        binding.enroll.isVisible = true
        binding.unenroll.isVisible = false

        val currentSportReference = userReference.child(sportViewModel.date.value.toString()).child(sportViewModel.sportId.value.toString())

        currentSportReference.removeValue()

        membersCurrent--
        sportReference.child(sportViewModel.date.value.toString()).child(sportViewModel.sportId.value.toString())
            .child("membersCurrent").setValue(membersCurrent)
        sportViewModel.sportMembersCurrent.value = membersCurrent
        Toast.makeText(
            requireContext(),
            "Запись отменена",
            Toast.LENGTH_SHORT
        ).show()
    }

}