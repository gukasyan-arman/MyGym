package com.example.mygym.screen.team

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygym.adapter.CoachAdapter
import com.example.mygym.databinding.FragmentTeamBinding
import com.example.mygym.model.Coach
import com.google.firebase.database.*

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private var dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("TRAINER")
    private lateinit var coachArrayList: ArrayList<Coach>

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
        dbReference.addValueEventListener(object : ValueEventListener{
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
                            Toast.makeText(requireContext(), "Click on element #$position",
                            Toast.LENGTH_SHORT).show()
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