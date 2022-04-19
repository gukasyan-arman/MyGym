package com.example.mygym.screen.team

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygym.adapter.CoachAdapter
import com.example.mygym.databinding.FragmentTeamBinding
import com.example.mygym.model.Coach
import com.google.firebase.database.*

class TeamFragment : Fragment() {

    lateinit var binding: FragmentTeamBinding
    lateinit var dbReference: DatabaseReference
    lateinit var coachArrayList: ArrayList<Coach>

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
        dbReference = FirebaseDatabase.getInstance().getReference("TRAINER")
        dbReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (coachSnapshot in snapshot.children) {
                        val coach = coachSnapshot.getValue(Coach::class.java)
                        coachArrayList.add(coach!!)
                    }
                    binding.teamRv.adapter = CoachAdapter(coachArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DatabaseError", error.toString())
            }

        })
    }

}