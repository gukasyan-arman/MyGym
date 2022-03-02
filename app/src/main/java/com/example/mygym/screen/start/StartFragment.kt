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
import com.example.mygym.NUMBER_OF_PAGER_ITEM
import com.example.mygym.R
import com.example.mygym.adapter.ViewPagerAdapter
import com.example.mygym.databinding.FragmentStartBinding
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator3

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    private var titlesList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val viewPager: ViewPager2 = requireActivity().findViewById(R.id.startViewPager)
//
//        val fragments: ArrayList<Fragment> = arrayListOf(
//            PagerItemFragment(),
//            PagerItem2Fragment(),
//            PagerItem3Fragment()
//        )
//
//        val adapter = ViewPagerAdapter(fragments, requireActivity() as AppCompatActivity)
//        viewPager.adapter = adapter

        postToList()

        startViewPager.adapter = ViewPagerAdapter(titlesList, imagesList)

        val indicator: CircleIndicator3 = view.findViewById(R.id.indicator)
        indicator.setViewPager(startViewPager)

    }

    private fun addToList(title: String, image: Int) {
        titlesList.add(title)
        imagesList.add(image)
    }

    private fun postToList() {
        for (i in 1.. NUMBER_OF_PAGER_ITEM) {
            addToList("Title $i", R.drawable.man_powerlift)
        }
    }

}