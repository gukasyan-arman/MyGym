package com.example.mygym.screen.personalarea

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.mygym.R
import com.example.mygym.databinding.FragmentPersonalAreaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.runBlocking

class PersonalAreaFragment : Fragment() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var binding: FragmentPersonalAreaBinding
    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private var databaseReference = FirebaseDatabase.getInstance().reference.child("users")
    private var currentUserReference: DatabaseReference = databaseReference.child(currentUser!!.phoneNumber.toString())
    private val userModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalAreaBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        runBlocking {
            currentUserReference.get().addOnSuccessListener { snapshot ->
                binding.firstNameTv.text = snapshot.child("firstName").value.toString()
                binding.lastNameTv.text = snapshot.child("lastName").value.toString()
                binding.dayOfBirth.text = snapshot.child("birthday").value.toString()
                binding.mailTv.text = snapshot.child("mail").value.toString()
                binding.sex.text = snapshot.child("gender").value.toString()
            }
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUserNumber = currentUser?.phoneNumber

        binding.welcomeTv.text = "Добро пожаловать\n$currentUserNumber"
        binding.phoneTv.text = currentUserNumber

        binding.logoutBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Выход выполнен", Toast.LENGTH_LONG).show()
            auth.signOut()

            userModel.user.value = auth.currentUser
            Navigation.findNavController(view).navigate(R.id.action_personalAreaFragment_to_startFragment)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.editPersonalDataFragment -> {
                Navigation.findNavController(view!!).navigate(R.id.action_personalAreaFragment_to_editPersonalDataFragment2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.personal_area_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}