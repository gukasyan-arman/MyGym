package com.example.mygym.screen.team

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mygym.R
import com.example.mygym.databinding.FragmentCoachBinding
import com.example.mygym.screen.personalarea.UserViewModel


class CoachFragment : Fragment() {

    lateinit var binding: FragmentCoachBinding
    private val coachViewModel: CoachViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoachBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        coachViewModel.coachId.observe(requireActivity(), Observer {
//            binding.coachDescription.text = coachViewModel.coachDescription.value.toString()
//            binding.coachName.text = coachViewModel.coachFirstName.value.toString() +
//                    " " + coachViewModel.coachLastName.value.toString()
//            binding.coachPost.text = coachViewModel.coachPost.value.toString()
//        })

        binding.coachDescription.text = coachViewModel.coachDescription.value.toString()
        binding.coachName.text = coachViewModel.coachFirstName.value.toString() +
                " " + coachViewModel.coachLastName.value.toString()
        binding.coachPost.text = coachViewModel.coachPost.value.toString()

    }
}