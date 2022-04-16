package com.example.mygym.screen.personalarea

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.red
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mygym.R
import com.example.mygym.databinding.FragmentEditPersonalDataBinding
import com.example.mygym.dialog.DatePickerFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

class EditPersonalDataFragment : Fragment() {

    private lateinit var binding: FragmentEditPersonalDataBinding
    private var databaseReference = FirebaseDatabase.getInstance().reference.child("users")
    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private var currentUserReference: DatabaseReference = databaseReference.child(currentUser!!.phoneNumber.toString())
    private var firstName: String = ""
    private var lastName: String = ""
    private var birthday: String = ""
    private var mail: String = ""
    private var gender: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditPersonalDataBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        runBlocking {
            currentUserReference.get().addOnSuccessListener { snapshot ->

                    firstName = snapshot.child("firstName").value.toString()
                    Log.d("editpersonaldata", "firstName = $firstName")

                    lastName = snapshot.child("lastName").value.toString()
                    Log.d("editpersonaldata", "lastName = $lastName")

                    birthday = snapshot.child("birthday").value.toString()
                    Log.d("editpersonaldata", "birthday = $birthday")

                    mail = snapshot.child("mail").value.toString()
                    Log.d("editpersonaldata", "mail = $mail")

                    gender = snapshot.child("gender").value.toString()
                    Log.d("editpersonaldata", "gender = $gender")

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUserReference.get().addOnSuccessListener {snapshot ->
            if (snapshot.child("firstName").value != "") {
                binding.updateFirstNameTextInputEditText.setText(snapshot.child("firstName").value.toString())
            }
            if (snapshot.child("lastName").value != "") {
                binding.updateLastNameTextInputEditText.setText(snapshot.child("lastName").value.toString())
            }
            if (snapshot.child("birthday").value != "") {
                binding.updateDateTextView.text = snapshot.child("birthday").value.toString()
            }
            if (snapshot.child("mail").value != "") {
                binding.updateEmailTextInputEditText.setText(snapshot.child("mail").value.toString())
            }
            if (snapshot.child("gender").value != "") {
                binding.updateSexTextView.text = snapshot.child("gender").value.toString()
            }
        }


        binding.updateDateLinearLayout.setOnClickListener {

        val datePickerFragment = DatePickerFragment()
        val supportFragmentManager = requireActivity().supportFragmentManager

        supportFragmentManager.setFragmentResultListener(
            "REQUEST_KEY", viewLifecycleOwner
        ) {
            resultKey, bundle -> if (resultKey == "REQUEST_KEY") {
                val date = bundle.getString("SELECTED_DATE")
            binding.updateDateTextView.text = date
            }
        }

        datePickerFragment.show(supportFragmentManager, "DatePickerFragment")

        }

        binding.updateSexTextView.setOnClickListener {

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.personalAreaFragment -> {

                if (validateInfo()) {
                    Toast.makeText(requireContext(), "Информация сохранена", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view!!).navigate(R.id.action_editPersonalDataFragment2_to_personalAreaFragment)
                } else {
                    Toast.makeText(requireContext(), "Заполните подчеркнутые поля", Toast.LENGTH_LONG).show()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun validateInfo(): Boolean {

        if (
            binding.updateFirstNameTextInputEditText.text.toString().trim().isNotEmpty() &&
            binding.updateLastNameTextInputEditText.text.toString().trim().isNotEmpty() &&
            binding.updateDateTextView.text.toString().trim().isNotEmpty() &&
            binding.updateEmailTextInputEditText.text.toString().trim().isNotEmpty() &&
            binding.updateSexTextView.text.toString().trim().isNotEmpty()
        ) {
            firstName = binding.updateFirstNameTextInputEditText.text.toString().trim()
            currentUserReference.child("firstName").setValue(firstName)

            lastName = binding.updateLastNameTextInputEditText.text.toString().trim()
            currentUserReference.child("lastName").setValue(lastName)

            birthday = binding.updateDateTextView.text.toString().trim()
            currentUserReference.child("birthday").setValue(birthday)

            mail = binding.updateEmailTextInputEditText.text.toString().trim()
            currentUserReference.child("mail").setValue(mail)

            gender = binding.updateSexTextView.text.toString().trim()
            currentUserReference.child("gender").setValue(gender)

            binding.errorSex.isVisible = false
            binding.errorDate.isVisible = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.lineUnderDate.setBackgroundColor(resources.getColor(R.color.black, null))
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.lineUnderSex.setBackgroundColor(resources.getColor(R.color.black, null))
            }

            return true
        } else {
            if (binding.updateFirstNameTextInputEditText.text.toString().trim().isEmpty()) {
                binding.updateFirstNameTextInputLayout.error = "Заполните имя"
            }
            if (binding.updateLastNameTextInputEditText.text.toString().trim().isEmpty()) {
                binding.updateLastNameTextInputLayout.error = "Заполните фамилию"
            }
            if (binding.updateDateTextView.text.toString().trim().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.lineUnderDate.setBackgroundColor(resources.getColor(R.color.sbd_red, null))
                }
                binding.errorDate.isVisible = true
            }
            if (binding.updateEmailTextInputEditText.text.toString().trim().isEmpty()) {
                binding.updateEmailTextInputLayout.error = "Заполните почту"
            }
            if (binding.updateSexTextView.text.toString().trim().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.lineUnderSex.setBackgroundColor(resources.getColor(R.color.sbd_red, null))
                }
                binding.errorSex.isVisible = true
            }
            return false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_personal_area_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}