package com.example.mygym.screen.personalarea

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mygym.R
import com.example.mygym.databinding.FragmentEditPersonalDataBinding
import com.example.mygym.dialog.DatePickerFragment

class EditPersonalDataFragment : Fragment() {

    lateinit var binding: FragmentEditPersonalDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditPersonalDataBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.personalAreaFragment -> {
                Toast.makeText(requireContext(), "Информация сохранена", Toast.LENGTH_SHORT).show()



                Navigation.findNavController(view!!).navigate(R.id.action_editPersonalDataFragment2_to_personalAreaFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_personal_area_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}