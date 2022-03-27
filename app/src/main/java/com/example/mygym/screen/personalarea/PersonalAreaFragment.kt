package com.example.mygym.screen.personalarea

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mygym.R
import com.example.mygym.databinding.FragmentPersonalAreaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class PersonalAreaFragment : Fragment() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var binding: FragmentPersonalAreaBinding
    private val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val userModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalAreaBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUserNumber = currentUser?.phoneNumber

        binding.welcomeTv.text = "Добро пожаловать\n$currentUserNumber"
        binding.phoneTv.text = currentUserNumber

        binding.logoutBtn.setOnClickListener {
            auth.signOut()
            Toast.makeText(requireContext(), "Выход выполнен", Toast.LENGTH_LONG).show()
            userModel.user.value = FirebaseAuth.getInstance().currentUser
            Navigation.findNavController(view).navigate(R.id.action_personalAreaFragment_to_startFragment)
        }

    }

}