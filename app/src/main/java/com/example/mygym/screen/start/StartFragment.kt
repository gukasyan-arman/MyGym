package com.example.mygym.screen.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mygym.R
import com.example.mygym.adapter.ViewPagerAdapter
import com.example.mygym.databinding.FragmentStartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = requireActivity().findViewById(R.id.startViewPager)

        val fragments: ArrayList<Fragment> = arrayListOf(
            PagerItemFragment(),
            PagerItem2Fragment(),
            PagerItem3Fragment()
        )

        val adapter = ViewPagerAdapter(fragments, requireActivity() as AppCompatActivity)
        viewPager.adapter = adapter
    }

}