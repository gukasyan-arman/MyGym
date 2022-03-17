package com.example.mygym.screen.personalarea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.mygym.R
import com.example.mygym.databinding.FragmentAuthBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential

class AuthFragment : Fragment() {

    lateinit var binding: FragmentAuthBinding
    private lateinit var auth: FirebaseAuth
    private var verificationId: String = ""

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
            val phoneNumber = binding.phoneEditText.text.toString().trim()
            if (phoneNumber.isEmpty() || phoneNumber.length < 10) {
                Toast.makeText(requireContext(), "Enter correct phone", Toast.LENGTH_SHORT).show()
                binding.phoneEditText.requestFocus()
            } else {
                binding.buttonVerify.isVisible = true
                binding.otpEditText.isVisible = true
                binding.buttonGetCode.text = "Получить код повторно"
//                sendVerificationCode(phoneNumTest)
                sendVerificationCode(phoneNumber)
            }
        }

        binding.buttonVerify.setOnClickListener {
            if (binding.otpEditText.text.toString().trim().isEmpty()) {
                Toast.makeText(requireContext(), "Неверный код", Toast.LENGTH_SHORT).show()
            } else {
                verifyCode(binding.otpEditText.text.toString())
//                verifyCode(smsCodeTest)
            }

        }

    }

    private fun sendVerificationCode(phoneNumber: String) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+7$phoneNumber")       // Phone number to verify
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
        firebaseAuth .signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view!!).navigate(R.id.action_global_personalAreaFragment)
            }
        }
    }

}
