package com.example.mygym.screen.timetable

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygym.R
import com.example.mygym.adapter.SportAdapter
import com.example.mygym.databinding.FragmentTimetableBinding
import com.example.mygym.dialog.DatePickerFragment
import com.example.mygym.model.Sport
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TimetableFragment : Fragment() {

    lateinit var binding: FragmentTimetableBinding
    private var databaseReference = FirebaseDatabase.getInstance().reference
    private var sportReference: DatabaseReference = databaseReference.child("lessons")
    private lateinit var sportArrayList: ArrayList<Sport>
    private val sportViewModel: SportViewModel by activityViewModels()
    private val dateViewModel: DateViewModel by activityViewModels()
    lateinit var date: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimetableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dateBtn.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            supportFragmentManager.setFragmentResultListener("REQUEST_KEY", viewLifecycleOwner) {
                resultKey, bundle -> if (resultKey == "REQUEST_KEY") {
                    date = bundle.getString("SELECTED_DATE").toString()
                }
                binding.dateBtn.text = date
                dateViewModel.date.value = date
                binding.sportsRv.layoutManager = LinearLayoutManager(context)
                sportArrayList = arrayListOf<Sport>()
                getSports(date)
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")


        }

    }

    private fun getSports(date: String) {
        sportReference.child(date).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.noSportsTv.isVisible = false
                sportArrayList.clear()
                if (snapshot.exists()) {
                    for (sportSnapshot in snapshot.children) {
                        val sport = sportSnapshot.getValue(Sport::class.java)
                        sportArrayList.add(sport!!)
                    }
                    val adapter = SportAdapter(sportArrayList)
                    binding.sportsRv.adapter = adapter
                    adapter.setOnItemClickListener(object : SportAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            sportReference.child(date).child("sport$position").get().addOnSuccessListener {snapshot ->
                                sportViewModel.sportTitle.value = snapshot.child("title").value.toString()
                                Log.d("sportInfo", "title :" + snapshot.child("title").value.toString())

                                sportViewModel.sportDescription.value = snapshot.child("description").value.toString()
                                Log.d("sportInfo", "description :" +  snapshot.child("description").value.toString())

                                sportViewModel.sportTime.value = snapshot.child("time").value.toString()
                                Log.d("sportInfo", "time :" +  snapshot.child("time").value.toString())

                                sportViewModel.sportDuration.value = snapshot.child("duration").value as Long?
                                Log.d("sportInfo", "duration :" +  snapshot.child("duration").value.toString())

                                sportViewModel.sportRoom.value = snapshot.child("room").value.toString()
                                Log.d("sportInfo", "room :" +  snapshot.child("room").value.toString())

                                sportViewModel.sportTrainer.value = snapshot.child("trainer").value.toString()
                                Log.d("sportInfo", "trainer :" +  snapshot.child("trainer").value.toString())

                                sportViewModel.sportMembersCurrent.value = snapshot.child("membersCurrent").value as Long?
                                Log.d("sportInfo", "membersCurrent :" +  snapshot.child("membersCurrent").value.toString())

                                sportViewModel.sportMembersMax.value = snapshot.child("membersMax").value as Long?
                                Log.d("sportInfo", "membersMax :" +  snapshot.child("membersMax").value.toString())

                            }

                            Navigation.findNavController(view!!).navigate(R.id.action_timetableFragment_to_sportFragment)

                        }
                    })
                } else {
                    binding.noSportsTv.isVisible = true
                    binding.sportsRv.isVisible = false
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DatabaseError", error.toString())
            }

        })
    }

}