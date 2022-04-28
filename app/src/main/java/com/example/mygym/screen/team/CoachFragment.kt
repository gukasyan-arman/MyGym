package com.example.mygym.screen.team

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mygym.R
import com.example.mygym.databinding.FragmentCoachBinding
import com.example.mygym.screen.personalarea.UserViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class CoachFragment : Fragment() {

    lateinit var binding: FragmentCoachBinding
    private val coachViewModel: CoachViewModel by activityViewModels()
    private lateinit var storageReference: StorageReference

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
        binding.coachPhoto.setImageResource(R.drawable.ic_baseline_person_24)

        binding.coachDescription.text = coachViewModel.coachDescription.value.toString()
        binding.coachName.text = coachViewModel.coachFirstName.value.toString() +
                " " + coachViewModel.coachLastName.value.toString()
        binding.coachPost.text = coachViewModel.coachPost.value.toString()
//        binding.coachPhoto.setImageBitmap(coachViewModel.coachPhoto.value)

        val imageId = coachViewModel.coachId.value

        storageReference = FirebaseStorage.getInstance().getReference("photo_of_coaches/$imageId.jpeg")
        Toast.makeText(context, "photo_of_coaches/$imageId.jpeg", Toast.LENGTH_LONG).show()

        val localFile: File = File.createTempFile("tempFile", ".jpeg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.coachPhoto.setImageBitmap(bitmap)

        }.addOnFailureListener{

        }

    }
}