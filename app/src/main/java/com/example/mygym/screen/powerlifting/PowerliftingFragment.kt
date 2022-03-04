package com.example.mygym.screen.powerlifting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygym.R
import com.example.mygym.adapter.ViewPagerImageAdapter
import com.example.mygym.databinding.FragmentPowerliftingBinding
import kotlinx.android.synthetic.main.fragment_about_gym.*
import me.relex.circleindicator.CircleIndicator3

class PowerliftingFragment : Fragment() {

    lateinit var binding: FragmentPowerliftingBinding
    private var imagesList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPowerliftingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postToList()

        imageViewPager.adapter = ViewPagerImageAdapter(imagesList)

        val indicator: CircleIndicator3 = view.findViewById(R.id.indicator)
        indicator.setViewPager(imageViewPager)

    }

    private fun addToList(image: Int) {
        imagesList.add(image)
    }


    //delete or add page of pager
    private fun postToList() {

        for (i in 1..5) {
            when (i) {
                1 -> addToList(R.drawable.man_powerlift)
                2 -> addToList(R.drawable.man_powerlift)
                3 -> addToList(R.drawable.man_powerlift)
                4 -> addToList(R.drawable.man_powerlift)
                5 -> addToList(R.drawable.man_powerlift)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        imagesList.clear()
    }

}