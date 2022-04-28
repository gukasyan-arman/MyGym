package com.example.mygym.screen.team

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygym.R
import com.example.mygym.adapter.CoachAdapter
import com.example.mygym.databinding.FragmentTeamBinding
import com.example.mygym.model.Coach
import com.example.mygym.screen.personalarea.UserViewModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.runBlocking
import java.io.File

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private var databaseReference = FirebaseDatabase.getInstance().reference
    private var trainerReference: DatabaseReference = databaseReference.child("TRAINER")
    private lateinit var coachArrayList: ArrayList<Coach>
    private val coachViewModel: CoachViewModel by activityViewModels()
    private lateinit var storageReference: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.teamRv.layoutManager = LinearLayoutManager(context)
        coachArrayList = arrayListOf<Coach>()
        getCoachData()

    }

    private fun getCoachData() {
        trainerReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                coachArrayList.clear()
                if (snapshot.exists()) {
                    for (coachSnapshot in snapshot.children) {
                        val coach = coachSnapshot.getValue(Coach::class.java)
                        coachArrayList.add(coach!!)
                    }
                    val adapter = CoachAdapter(coachArrayList)
                    binding.teamRv.adapter = adapter
                    adapter.setOnItemClickListener(object : CoachAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            trainerReference.child("trainer$position").get().addOnSuccessListener {snapshot ->
                                coachViewModel.coachId.value = snapshot.child("id").value.toString()
                                Log.d("coachInfo", "id :" + snapshot.child("id").value.toString())

                                coachViewModel.coachFirstName.value = snapshot.child("firstName").value.toString()
                                Log.d("coachInfo", "firstName :" +  snapshot.child("firstName").value.toString())

                                coachViewModel.coachLastName.value = snapshot.child("lastName").value.toString()
                                Log.d("coachInfo", "lastName :" +  snapshot.child("lastName").value.toString())

                                coachViewModel.coachPost.value = snapshot.child("post").value.toString()
                                Log.d("coachInfo", "post :" +  snapshot.child("post").value.toString())

                                coachViewModel.coachDescription.value = snapshot.child("description").value.toString()
                                Log.d("coachInfo", "description :" +  snapshot.child("description").value.toString())

                            }

                            Navigation.findNavController(view!!).navigate(R.id.action_teamFragment2_to_coachFragment)

                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DatabaseError", error.toString())
            }

        })
    }

}