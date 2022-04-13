package com.example.mygym.screen.personalarea

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
import com.example.mygym.databinding.FragmentAuthBinding
import com.example.mygym.model.User
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.database.*

class AuthFragment : Fragment() {

    lateinit var binding: FragmentAuthBinding
    private lateinit var auth: FirebaseAuth
    private var verificationId: String = ""
    private lateinit var phoneNumber: String
    private val ruRegionNumber = "+7"
    private val userModel: UserViewModel by activityViewModels()

    private lateinit var database: DatabaseReference
    private lateinit var usersDatabaseReference: DatabaseReference

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
        database = FirebaseDatabase.getInstance().reference
        usersDatabaseReference = FirebaseDatabase.getInstance().reference.child("users")

        binding.buttonGetCode.setOnClickListener {
            phoneNumber = binding.phoneEditText.text.toString().trim()
            Toast.makeText(requireContext(), phoneNumber, Toast.LENGTH_LONG).show()
            validatePhone(phoneNumber)
        }

        binding.buttonVerify.setOnClickListener {
            validateCode(binding.otpEditText.toString().trim())
        }

    }

    private fun sendVerificationCode(phoneNumber: String) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(ruRegionNumber + phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private var callbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            val code: String = credential.smsCode.toString()
            verifyCode(code)
            signInByCredentials(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
           Toast.makeText(requireContext(), "Verification failed, try again", Toast.LENGTH_LONG).show()
        }

        override fun onCodeSent(
            s: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(s, token)
            verificationId = s
        }
    }

    private fun verifyCode(Code: String) {
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, Code)
        signInByCredentials(credential)
    }

    private fun signInByCredentials(credential: PhoneAuthCredential) {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        phoneNumber = binding.phoneEditText.text.toString().trim()
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val user = auth.currentUser
                userModel.user.value = user

                usersDatabaseReference.get().addOnSuccessListener { snapshot ->
                    if (!snapshot.hasChild(ruRegionNumber + phoneNumber)) {
                        createUser(user)
                        Toast.makeText(requireContext(), "Регистрация выполнена", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Вход выполнен", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view!!).navigate(R.id.action_authFragment_to_personalAreaFragment)
                    }
                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
            }
        }
    }

    private fun createUser(firebaseUser: FirebaseUser?) {
        val user = User(
            firebaseUser?.phoneNumber,
//            firebaseUser?.uid,
            phoneNumber,
            "",
            "",
            "",
            "",
            "",
        )

        usersDatabaseReference.child(firebaseUser?.phoneNumber!!).setValue(user)
//        usersDatabaseReference.push().setValue(user)
        Navigation.findNavController(view!!).navigate(R.id.action_authFragment_to_editPersonalDataFragment2)
    }

    private fun validatePhone(phoneNumber: String): Boolean {
        return if (binding.acceptRules.isChecked) {
            return if (phoneNumber.isEmpty()) {
                binding.phoneTextInputLayout.error = "Please, input your phone number"
                false
            } else if (phoneNumber.isNotEmpty() && phoneNumber.length < 10) {
                binding.phoneTextInputLayout.error = "Please, input correct phone number"
                false
            } else {
                binding.buttonVerify.isVisible = true
                binding.otpTextInputLayout.isVisible = true
                binding.buttonGetCode.text = "Получить код повторно"
                sendVerificationCode(phoneNumber)
                binding.phoneTextInputLayout.error = ""
                true
            }
        } else {
            Toast.makeText(requireContext(), "Примите правила", Toast.LENGTH_SHORT).show()
            false
        }

    }

    private fun validateCode(code: String): Boolean {
        return if (binding.otpEditText.text.toString().trim().isEmpty()) {
            binding.otpTextInputLayout.error = "Please, input your code"
            false
        } else if (code.isNotEmpty() && code.length < 6) {
            binding.otpTextInputLayout.error = "Please, input correct code"
            false
        } else {
            verifyCode(binding.otpEditText.text.toString())
            true
        }
    }

}
